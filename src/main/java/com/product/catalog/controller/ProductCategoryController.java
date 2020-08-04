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

import com.product.catalog.pojo.ManufacturerDto;
import com.product.catalog.pojo.ProductCategoryDto;
import com.product.catalog.service.ProductCategoryService;

import ch.qos.logback.classic.Logger;
@RestController
@RequestMapping("/ProductCategory")
public class ProductCategoryController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductCategoryController.class);
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductCategoryDto> saveProductCategory(@RequestBody ProductCategoryDto productCategoryDto){
		logger.debug("save productCategory ");
		ProductCategoryDto productCatRes = productCategoryService.saveProductCategory(productCategoryDto); 
		return new ResponseEntity<ProductCategoryDto>(productCatRes, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductCategoryDto> updateProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
		logger.debug("update productCategory ");
		ProductCategoryDto productCatRes = productCategoryService.saveProductCategory(productCategoryDto);
		return new ResponseEntity<ProductCategoryDto>(productCatRes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/{productCategoryId}")
	public ResponseEntity<ProductCategoryDto> getProductCategoryById(@PathVariable("productCategoryId") Long productCategoryId){
		logger.debug("get productCategory ");
		return new ResponseEntity<ProductCategoryDto>(productCategoryService.getProductCategoryById(productCategoryId), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{productCategoryId}")
	public ResponseEntity<?> deleteProductCategoryById(@PathVariable("productCategoryId") Long productCategoryId){
		logger.debug("delete productCategory ");
		productCategoryService.deleteProductCategoryById(productCategoryId);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping(value = "/get/all")
	public ResponseEntity<List<ProductCategoryDto>> getAllProductCategories(){
		return new ResponseEntity<List<ProductCategoryDto>>(productCategoryService.getAllProductCategories(), HttpStatus.OK);
	}
}
