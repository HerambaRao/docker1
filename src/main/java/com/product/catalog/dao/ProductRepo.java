package com.product.catalog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.catalog.ents.Product;

public interface ProductRepo  extends JpaRepository<Product, Long>{

}
