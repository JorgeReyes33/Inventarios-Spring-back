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
    public ResponseEntity<ProductResponseRest> searchById(Long id);
    public ResponseEntity<ProductResponseRest> searchByName(String name);
    public ResponseEntity<ProductResponseRest> deleteById(Long id);
    public ResponseEntity<ProductResponseRest> search();
    public ResponseEntity<ProductResponseRest> update(Product product, Long categoryId, Long id);


    
    

}
