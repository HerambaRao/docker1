package com.product.catalog.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.catalog.model.Product;
import com.product.catalog.model.ProductCategory;
import com.product.catalog.service.ProductService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		logger.info("Saving Product");
		Product productRes = productService.saveProduct(product); 
		return new ResponseEntity<Product>(productRes, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		logger.info("Update Product");
		Product productRes = productService.saveProduct(product);
		return new ResponseEntity<Product>(productRes, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
		logger.info("get Product");
		Product prodcut = null;
		try {
			prodcut = productService.getProductById(productId);
		} catch( Exception ex) {
			throw new RuntimeException("Processing Error");
		}
		return new ResponseEntity<Product>(prodcut , HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") Long productId){
		logger.info("Delete Product");
		productService.deleteProductById(productId);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping(value = "/get/all")
	public ResponseEntity<List<Product>> getAllProducts(){
		return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
	}
	
	
	
}
