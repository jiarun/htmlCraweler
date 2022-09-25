package com.jpc.tools.pgc.schedule;

import com.jpc.tools.pgc.config.SchedulingConfig;
import com.jpc.tools.pgc.util.WebCrawlerException;
import com.jpc.tools.pgc.vo.WebPageConfig;
import com.jpc.tools.pgc.vo.WebParseResults;
import com.jpc.tools.pgc.vo.KeyValue;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("hcScheduler")
public class WebCrawlerScheduler {

    private final static Logger logger = LoggerFactory.getLogger(WebCrawlerScheduler.class.getName());

    private Map<String, WebParseResults> webParser = new HashMap<>();

    @Autowired
    private SchedulingConfig schedulingConfig;

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void scheduleTask() throws WebCrawlerException {
        try {
            var webPages = schedulingConfig.getWebPageConfigurations();
            webParser.clear();
            for (WebPageConfig h : webPages) {

                WebParseResults webParseResults = new WebParseResults();
                webParseResults.setPageName(h.getPageName());
                webParseResults.setTimeStamp(new Date());
                webParser.put(h.getKey(), webParseResults);
                try {
                    logger.debug("launch url - " + h.getUrl());
                    Connection connect = Jsoup.connect(h.getUrl());
                    long time = System.currentTimeMillis();
                    connect.get();

                    Response res = connect.response();
                    Document onlineDocument = res.parse();

                    webParseResults.setAccessStatus(res.statusCode() + "").
                            setResponseTime(System.currentTimeMillis() - time).setOnline(true);
                    logger.debug("Response Status  - " + res.statusCode());
                    logger.debug("Response Time - " + webParseResults.getResponseTime());
                    for (String lookUpWord : h.getLookupWords()) {
                        webParseResults.addPair(new KeyValue(lookUpWord, onlineDocument.html().contains(lookUpWord) + ""));
                    }

                } catch (HttpStatusException e) {
                    webParseResults.setOnline(false);
                } catch (IOException e) {
                    logger.error("Jsoup read failed", e);
                }

            }
        } catch (Exception e) {
            throw new WebCrawlerException("Jsoup read failed", e);
        }
    }

    @Bean
    public Collection<WebParseResults> getResult() {
        return webParser.values();
    }

}
