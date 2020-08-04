package com.product.catalog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.ProductApplication;
import com.product.catalog.dao.ProductCategoryRepo;
import com.product.catalog.ents.ProductCategory;
import com.product.catalog.pojo.ProductCategoryDto;

import ch.qos.logback.classic.Logger;

@Service
public class ProductCategoryService {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductCategoryService.class);
	@Autowired
	private ProductCategoryRepo productCategoryRepo;
	public static Map<Long, String> productCatMap = new HashMap<>();

	public ProductCategoryDto saveProductCategory(ProductCategoryDto categoryDto) {
		ProductCategory category = productCategoryRepo.save(getEntity(categoryDto));
		return getDto(category);
	}

	public ProductCategoryDto getProductCategoryById(Long productCategoryId) {
		ProductCategory category = productCategoryRepo.findById(productCategoryId).get();
		return getDto(category);
	}

	public void deleteProductCategoryById(Long productCategoryId) {

		productCategoryRepo.deleteById(productCategoryId);
	}

	public List<ProductCategoryDto> getAllProductCategories() {
		List<ProductCategoryDto> list = new ArrayList<ProductCategoryDto>();
		productCategoryRepo.findAll().forEach(category -> {
			list.add(getDto(category));
		});
		return list;
	}

	public ProductCategory getEntity(ProductCategoryDto dto) {
		ProductCategory category = new ProductCategory();
		category.setCategory(dto.getCategory());
		category.setDescription(dto.getDescription());
		category.setId(dto.getId());
		return category;
	}

	public ProductCategoryDto getDto(ProductCategory productCategory) {
		ProductCategoryDto dto = new ProductCategoryDto();

		dto.setCategory(productCategory.getCategory());
		dto.setDescription(productCategory.getDescription());
		dto.setId(productCategory.getId());
		return dto;
	}
	
	public void loadProductCat() {
		
		productCategoryRepo.findAll().forEach(category -> {
			productCatMap.put(category.getId(), category.getCategory());
		});
		
		logger.debug("productCatMap Size ***"+ productCatMap.size());
	}

}
