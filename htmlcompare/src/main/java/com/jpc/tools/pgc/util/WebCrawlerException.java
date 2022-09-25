package com.jpc.tools.pgc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpc.tools.pgc.schedule.WebCrawlerScheduler;

public class WebCrawlerException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = LoggerFactory.getLogger(WebCrawlerScheduler.class.getName());

	public WebCrawlerException() {
		super();
	}

	public WebCrawlerException(String exception, Throwable e) {
		super(exception, e);
		logger.error(exception, e);
	}
}
