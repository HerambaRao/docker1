package com.product.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.dao.SalesRepo;
import com.product.catalog.model.Sales;

@Service
public class SalesService {

	@Autowired
	private SalesRepo salesRepo;
	
	public Sales saveSalesService(Sales sales) {
		
		return salesRepo.save(sales);
	}
	
	public Sales getSalesById(Long salesId) {
		
		return salesRepo.findById(salesId).get();
	}
	
	public void deleteSalesById(Long salesId) {
		
		salesRepo.deleteById(salesId);
	}
	
	public List<Sales> getAllSales(){
		
		return salesRepo.findAll();
	}
}
