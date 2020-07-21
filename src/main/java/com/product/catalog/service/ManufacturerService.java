package com.product.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.dao.ManufacturerRepo;
import com.product.catalog.model.Manufacturer;

@Service
public class ManufacturerService {
	
	@Autowired
	private ManufacturerRepo manufacturerRepo;
	
	public Manufacturer saveManufacturer(Manufacturer manufacturer) {
		
		return manufacturerRepo.save(manufacturer);
	}
	
	public Manufacturer getManufacturerById(Long manufacturerId) {
		
		return manufacturerRepo.findById(manufacturerId).get();
	}
	
	public void deleteManufacturerById(Long manufacturerId) {
		
		manufacturerRepo.deleteById(manufacturerId);
	}
	
	public List<Manufacturer> getAllManufacturers() {
		
		return manufacturerRepo.findAll();
	}
}
