package com.jpc.tools.pgc.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jpc.tools.pgc.util.WebCrawlerException;
import com.jpc.tools.pgc.util.JsonUtils;
import com.jpc.tools.pgc.vo.WebPageConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SchedulingConfig {

    private final static Logger logger = LoggerFactory.getLogger(SchedulingConfig.class.getName());

    @Value("classpath:WebComparisionConufig.json")
    private Resource resourceFile;

    @Bean
    public TaskScheduler taskScheduler() {
        logger.debug("taskscheduler set thread name");
        ThreadPoolTaskScheduler tsk = new ThreadPoolTaskScheduler();
        tsk.setPoolSize(1);
        tsk.setThreadNamePrefix("HCScheduler");
        return tsk;
    }

    @Bean
    public List<WebPageConfig> getWebPageConfigurations() throws WebCrawlerException {
        List<WebPageConfig> properties;
        try {
            properties = JsonUtils.fromJSON(new TypeReference<List<WebPageConfig>>() {
            }, resourceFile.getInputStream());
        } catch (IOException e) {
            throw new WebCrawlerException();
        }
        return properties;
    }

}
