package com.oswi.inventory.inventarios.dao;

import org.springframework.data.repository.CrudRepository;

import com.oswi.inventory.inventarios.models.Product;

public interface IProductDao extends CrudRepository<Product, Long> {

}
