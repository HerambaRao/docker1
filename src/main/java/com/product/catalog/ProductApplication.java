package com.product.catalog;


import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Logger;



@SpringBootApplication
public class ProductApplication {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductApplication.class);
	public static void main(String[] args) {
		logger.debug("Starting Product Application ***");
		SpringApplication.run(ProductApplication.class, args);
	}

}
