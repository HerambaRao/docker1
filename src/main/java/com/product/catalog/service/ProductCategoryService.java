package com.product.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.dao.ProductCategoryRepo;
import com.product.catalog.model.ProductCategory;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepo productCategoryRepo;
	
	
	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		
		 return productCategoryRepo.save(productCategory);
	}
	
	public ProductCategory getProductCategoryById(Long productCategoryId) {
		
		 return productCategoryRepo.findById(productCategoryId).get();
	}
	
	public void deleteProductCategoryById(Long productCategoryId) {
		
		  productCategoryRepo.deleteById(productCategoryId);
	}

	public List<ProductCategory> getAllProductCategories(){
		
		return productCategoryRepo.findAll();
	}

}
