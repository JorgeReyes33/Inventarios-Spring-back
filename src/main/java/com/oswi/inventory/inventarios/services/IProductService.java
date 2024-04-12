package com.oswi.inventory.inventarios.services;

import org.springframework.http.ResponseEntity;

import com.oswi.inventory.inventarios.models.Product;
import com.oswi.inventory.inventarios.responses.ProductResponseRest;

public interface IProductService {

    /**
     * Metodo save que recibe los parametros siguientes: 
     * @param product
     * @param categoryId
     * @return
     */
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);

}
