package com.product.catalog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.catalog.model.Manufacturer;

public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long>{

}
