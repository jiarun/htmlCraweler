package com.jpc.tools.pgc.controller;

import com.jpc.tools.pgc.config.SchedulingConfig;
import com.jpc.tools.pgc.schedule.WebCrawlerScheduler;
import com.jpc.tools.pgc.util.WebCrawlerException;
import com.jpc.tools.pgc.vo.WebPageConfig;
import com.jpc.tools.pgc.vo.WebParseResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebCrawlerController {

    @Autowired
    private SchedulingConfig schConfig;

    @Autowired
    private WebCrawlerScheduler hcs;

    @CrossOrigin
    @GetMapping(value = "api/getWebConfigList")
    public List<WebPageConfig> getHtmlConfigurations() throws WebCrawlerException {
        return schConfig.getWebPageConfigurations();
    }

    @CrossOrigin
    @GetMapping(value = "api/getWebSearchReport")
    public List<WebParseResults> getHtmlReport() throws WebCrawlerException {
        return new ArrayList<>(hcs.getResult());
    }
}
