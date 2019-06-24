package com.eli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringCloundLearnApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringCloundLearnApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringCloundLearnApplication.class, args);
	}

	@PostConstruct
	public void log(){
		logger.trace("trace log");
		logger.debug("debug log");
		logger.info("info log");
		logger.warn("warn log");
		logger.error("error log");
	}

}
