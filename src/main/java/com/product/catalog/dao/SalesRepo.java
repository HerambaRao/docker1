package com.product.catalog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.catalog.ents.Sales;

public interface SalesRepo extends JpaRepository<Sales, Long>{

}
