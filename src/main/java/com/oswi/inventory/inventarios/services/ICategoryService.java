package com.oswi.inventory.inventarios.services;

import org.springframework.http.ResponseEntity;

import com.oswi.inventory.inventarios.models.Category;
import com.oswi.inventory.inventarios.responses.CategoryResponseRest;

public interface ICategoryService {

    //Con Response Entity podemos mandar las respuestas hacia el front, mandar una respuesta custom
    public ResponseEntity<CategoryResponseRest> search();
    
    public ResponseEntity<CategoryResponseRest> searchById(Long id);

    public ResponseEntity<CategoryResponseRest> save(Category category);

    //Este metodo update recibe un objeto de categoria y el id 
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id);




    
}
