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

import com.product.catalog.pojo.ProductDto;
import com.product.catalog.pojo.ProductCategoryDto;
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
	public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto){
		logger.info("Saving Product");
		ProductDto productRes = productService.saveProduct(productDto); 
		return new ResponseEntity<ProductDto>(productRes, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
		logger.info("Update Product");
		ProductDto productRes = productService.saveProduct(productDto);
		return new ResponseEntity<ProductDto>(productRes, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Long productId){
		logger.info("get Product");
		ProductDto prodcut = null;
		try {
			prodcut = productService.getProductById(productId);
		} catch( Exception ex) {
			throw new RuntimeException("Processing Error");
		}
		return new ResponseEntity<ProductDto>(prodcut , HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") Long productId){
		logger.info("Delete Product");
		productService.deleteProductById(productId);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping(value = "/get/all")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		return new ResponseEntity<List<ProductDto>>(productService.getAllProducts(), HttpStatus.OK);
	}
	
	
	
}
