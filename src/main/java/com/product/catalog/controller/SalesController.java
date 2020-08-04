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

import com.product.catalog.pojo.SalesBarChart;
import com.product.catalog.pojo.SalesDto;
import com.product.catalog.service.SalesService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/sales")
public class SalesController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(SalesController.class);
	
	@Autowired
	private SalesService salesService;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalesDto> saveSales(@RequestBody SalesDto salesDto){
		logger.info("Save Sales");
		return new ResponseEntity<SalesDto>(salesService.saveSalesService(salesDto), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalesDto> updateSales(@RequestBody SalesDto salesDto){
		logger.info("Update Sales");
		return new ResponseEntity<SalesDto>(salesService.saveSalesService(salesDto), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/{salesId}")
	public ResponseEntity<SalesDto> getSalesById(@PathVariable("salesId") Long salesId){
		logger.info("get Sales");
		return new ResponseEntity<SalesDto>(salesService.getSalesById(salesId), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{salesId}")
	public ResponseEntity<?> deleteSalesById(@PathVariable("salesId") Long salesId){
		logger.info("delete Sales");
		salesService.deleteSalesById(salesId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/get/all")
	public ResponseEntity<List<SalesDto>> getAllSales(){
		return new ResponseEntity<List<SalesDto>>(salesService.getAllSales(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/salesChart")
	public ResponseEntity<SalesBarChart> getSalesChart(){
		return new ResponseEntity<SalesBarChart>(salesService.getSalesChart(), HttpStatus.OK);
	}
}
