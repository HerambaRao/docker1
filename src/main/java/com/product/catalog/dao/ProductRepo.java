package com.product.catalog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.catalog.model.Product;

public interface ProductRepo  extends JpaRepository<Product, Long>{

}
