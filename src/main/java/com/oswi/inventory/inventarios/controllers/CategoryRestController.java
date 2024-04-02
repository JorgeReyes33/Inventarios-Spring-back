package com.oswi.inventory.inventarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oswi.inventory.inventarios.responses.CategoryResponseRest;
import com.oswi.inventory.inventarios.services.ICategoryService;

//Clase controladora de tipo Rest.

@RestController //Definir este servicio para poder usarlo como un ApiREST
@RequestMapping("/api/v1") //Se define la ruta principal  
public class CategoryRestController {

    @Autowired //Inyeccion de dependencias, en este caso del metodo search.
    private ICategoryService service;

    //Obtener todas las categorias
    @GetMapping("/categories") //Anotacion para metodo get, con esto se van a listar todas las categorias.
    public ResponseEntity<CategoryResponseRest> searchCategories() {

        ResponseEntity<CategoryResponseRest> response = service.search();
        return response; 

    }

    //Obtener las categorias por id 
    @GetMapping("/categories/{id}") 
    public ResponseEntity<CategoryResponseRest> searchCategoriesById( @PathVariable Long id ) {

        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response; 

    }
    
}
