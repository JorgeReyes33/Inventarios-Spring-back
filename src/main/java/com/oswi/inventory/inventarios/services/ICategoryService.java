package com.oswi.inventory.inventarios.services;

import org.springframework.http.ResponseEntity;

import com.oswi.inventory.inventarios.responses.CategoryResponseRest;

public interface ICategoryService {

    //Con Response Entity podemos mandar las respuestas hacia el front, mandar una respuesta custom
    public ResponseEntity<CategoryResponseRest> search();
    
}
