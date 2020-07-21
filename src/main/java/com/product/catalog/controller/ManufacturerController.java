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

import com.product.catalog.model.Manufacturer;
import com.product.catalog.service.ManufacturerService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/Manufacturer")
public class ManufacturerController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ManufacturerController.class);
	
	@Autowired
	private ManufacturerService manufacturerService ;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manufacturer> saveManufacturer(@RequestBody Manufacturer manufacturer){
		logger.info("Saving the manufacturer details");
		return new ResponseEntity<Manufacturer>(manufacturerService.saveManufacturer(manufacturer), HttpStatus.CREATED);
		
	}
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Manufacturer> updateManufacturer(@RequestBody Manufacturer manufacturer){
		logger.info("Updating the manufacturer details");
		return new ResponseEntity<Manufacturer>(manufacturerService.saveManufacturer(manufacturer), HttpStatus.OK);
		
	}
	@GetMapping(value = "/get/{manufacturerId}")
	public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable("manufacturerId") Long manufacturerId){
		logger.info("get the manufacturer details");
		return new ResponseEntity<Manufacturer>(manufacturerService.getManufacturerById(manufacturerId), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{manufacturerId}")
	public ResponseEntity<?> deleteManufacturerById(@PathVariable("manufacturerId") Long manufacturerId){
		logger.info("delete the manufacturer details");
		manufacturerService.deleteManufacturerById(manufacturerId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/get/all")
	public ResponseEntity<List<Manufacturer>> getAllManufacturers(){
		return new ResponseEntity<List<Manufacturer>>(manufacturerService.getAllManufacturers(), HttpStatus.OK);
	}
	
}
