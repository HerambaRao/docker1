package com.product.catalog;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.product.catalog.service.ProductCategoryService;
import com.product.catalog.service.ProductService;
import com.product.catalog.service.SalesService;

import ch.qos.logback.classic.Logger;



@SpringBootApplication
public class ProductApplication implements CommandLineRunner{
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductApplication.class);
	
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SalesService salesService;
	public static void main(String[] args) {
		logger.debug("Starting Product Application ***");
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		logger.debug("Loading Products ***");
		productCategoryService.loadProductCat();
		productService.loadProducts();
		salesService.getSalesChart();
	}

}
