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
import com.product.catalog.pojo.SalesBarChart;
import com.product.catalog.service.ManufacturerService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/Manufacturer")
public class ManufacturerController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ManufacturerController.class);
	
	@Autowired
	private ManufacturerService manufacturerService ;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ManufacturerDto> saveManufacturer(@RequestBody ManufacturerDto manufacturerDto){
		logger.info("Saving the manufacturer details");
		return new ResponseEntity<ManufacturerDto>(manufacturerService.saveManufacturer(manufacturerDto), HttpStatus.CREATED);
		
	}
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ManufacturerDto> updateManufacturer(@RequestBody ManufacturerDto manufacturerDto){
		logger.info("Updating the manufacturer details");
		return new ResponseEntity<ManufacturerDto>(manufacturerService.saveManufacturer(manufacturerDto), HttpStatus.OK);
		
	}
	@GetMapping(value = "/get/{manufacturerId}")
	public ResponseEntity<ManufacturerDto> getManufacturerById(@PathVariable("manufacturerId") Long manufacturerId){
		logger.info("get the manufacturer details");
		return new ResponseEntity<ManufacturerDto>(manufacturerService.getManufacturerById(manufacturerId), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{manufacturerId}")
	public ResponseEntity<?> deleteManufacturerById(@PathVariable("manufacturerId") Long manufacturerId){
		logger.info("delete the manufacturer details");
		manufacturerService.deleteManufacturerById(manufacturerId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/get/all")
	public ResponseEntity<List<ManufacturerDto>> getAllManufacturers(){
		return new ResponseEntity<List<ManufacturerDto>>(manufacturerService.getAllManufacturers(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/manufacturerChart")
	public ResponseEntity<SalesBarChart> getManufacturerChart(){
		return new ResponseEntity<SalesBarChart>(manufacturerService.getManufacturerChart(), HttpStatus.OK);
	}
}
