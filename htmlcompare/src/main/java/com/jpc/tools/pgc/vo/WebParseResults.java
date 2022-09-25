package com.jpc.tools.pgc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WebParseResults {

    private String pageName;
    private String accessStatus;
    private Boolean online;
    private Boolean found;
    private Long responseTime;
    private List<KeyValue> listPair;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date timeStamp;

    public String getPageName() {
        return pageName;
    }

    public WebParseResults setPageName(String pageName) {
        this.pageName = pageName;
        return this;
    }

    public String getAccessStatus() {
        return accessStatus;
    }

    public WebParseResults setAccessStatus(String accessStatus) {
        this.accessStatus = accessStatus;
        return this;
    }

    public Boolean getOnline() {
        return online;
    }

    public WebParseResults setOnline(Boolean online) {
        this.online = online;
        return this;
    }

    public Boolean getFound() {
        return found;
    }

    public void setFound(Boolean found) {
        this.found = found;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public WebParseResults setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public void addPair(KeyValue v) {
        if (getListPair() == null)
            listPair = new ArrayList<>();
        getListPair().add(v);
    }

    public WebParseResults setListPair(List<KeyValue> listPair) {
        this.listPair = listPair;
        return this;
    }

    public List<KeyValue> getListPair() {
        return listPair;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }


}
