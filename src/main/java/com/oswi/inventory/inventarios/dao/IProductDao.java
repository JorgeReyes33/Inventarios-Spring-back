package com.oswi.inventory.inventarios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.oswi.inventory.inventarios.models.Product;

public interface IProductDao extends CrudRepository<Product, Long> {

    //Realizar consultas personalizadas
    //Estos metodos nos permitiran hacer consultas por nombre, son dos posibles alternativas

    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByName(String name);

    List<Product> findByNameContainingIgnoreCase(String name);



}
