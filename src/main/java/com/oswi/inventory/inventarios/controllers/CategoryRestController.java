package com.oswi.inventory.inventarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oswi.inventory.inventarios.models.Category;
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

    /**
     * Metodo para buscar categorias por id 
     * @param id
     * @return
     */
    @GetMapping("/categories/{id}") 
    public ResponseEntity<CategoryResponseRest> searchCategoriesById( @PathVariable Long id ) {

        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response; 

    }
    

    //Guardar un registro de categoria 
    /*
     * @RequestBody -> Es el cuerpo que se va a enviar mediante el metodo post, para guardar el objeto
     * de categoria. Recuperar el objeto json que viene del cliente y lo convierte a un objeto Category.
    */
    @PostMapping("/categories") 
    public ResponseEntity<CategoryResponseRest> save( @RequestBody Category category ) {

        ResponseEntity<CategoryResponseRest> response = service.save(category);
        return response; 

    }


    /**
     * Metodo para actualizar un registro mediante el metodo PUT
     * @param category
     * @param id
     * @return
     */
    @PutMapping("/categories/{id}") 
    public ResponseEntity<CategoryResponseRest> update( @RequestBody Category category, @PathVariable Long id ) {

        ResponseEntity<CategoryResponseRest> response = service.update(category, id);
        return response; 

    }


    /**
     * Metodo para eliminar categorias
     * @param id
     * @return
     */
    @DeleteMapping("/categories/{id}") 
    public ResponseEntity<CategoryResponseRest> delete( @PathVariable Long id ) {

        ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
        return response; 

    }




}
