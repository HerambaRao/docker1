package com.product.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.dao.ProductRepo;
import com.product.catalog.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo ;
	
	public Product saveProduct(Product product) {
		
		return productRepo.save(product);
	}
	
	public Product getProductById(Long productId) {
		
		return productRepo.findById(productId).get();
	}

	public void deleteProductById(Long productId) {
		
		productRepo.deleteById(productId);
	}
	
	public List<Product> getAllProducts(){
		
		return productRepo.findAll();
	}
}
