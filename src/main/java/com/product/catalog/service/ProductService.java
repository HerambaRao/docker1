package com.product.catalog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.dao.ProductRepo;
import com.product.catalog.ents.Product;
import com.product.catalog.pojo.ProductDto;

import ch.qos.logback.classic.Logger;

@Service
public class ProductService {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductService.class);
	@Autowired
	private ProductRepo productRepo;
	public static Map<Long, String> productMap = new HashMap<>();


	public ProductDto saveProduct(ProductDto productDto) {
		Product product = productRepo.save(getEntity(productDto));
		return getDto(product);
	}

	public ProductDto getProductById(Long productId) {
		Product product = productRepo.findById(productId).get();
		return getDto(product);
	}

	public void deleteProductById(Long productId) {

		productRepo.deleteById(productId);
	}

	public List<ProductDto> getAllProducts() {
		List<ProductDto> list = new ArrayList<>();
		productRepo.findAll().forEach(product -> {
			list.add(getDto(product));
		});
		return list;
	}

	public Product getEntity(ProductDto dto) {
		Product product = new Product();
		product.setCategoryId(dto.getCategoryId());
		product.setDescription(dto.getDescription());
		product.setId(dto.getId());
		product.setName(dto.getName());
		return product;
	}

	public ProductDto getDto(Product product) {
		ProductDto dto = new ProductDto();
		dto.setCategoryId(product.getCategoryId());
		dto.setDescription(product.getDescription());
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setCategoryName(ProductCategoryService.productCatMap.get(product.getCategoryId()));
		return dto;
	}
	
public void loadProducts() {
	productRepo.findAll().forEach(product -> {
		productMap.put(product.getId(), product.getName());
		logger.debug("productMap Size ***"+ productMap.size());
	});
	}
}
