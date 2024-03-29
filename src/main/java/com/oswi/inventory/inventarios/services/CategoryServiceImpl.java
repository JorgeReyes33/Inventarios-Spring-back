package com.oswi.inventory.inventarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oswi.inventory.inventarios.dao.ICategoryDao;
import com.oswi.inventory.inventarios.models.Category;
import com.oswi.inventory.inventarios.responses.CategoryResponseRest;

import org.springframework.transaction.annotation.Transactional;

@Service //Definir nuestra clase como un servicio.
public class CategoryServiceImpl implements ICategoryService{

    /*
       @Autowired 
     * La inyeccion de dependencias te permite usar los metodos de otra clase
     * sin necesidad de declara una instancia local en la clase.
     * En este caso estamos teniendo acceso a los metodos de la interfaz ICategoryDao
     * que es donde se encuentra nuestro CrudRepository, y asi accedemos a todos sus metodos.
    */
    @Autowired 
    private ICategoryDao categoryDao;

    //Implementamos aqui en el servicio el metodo search que definimos en la interfaz ICategoryServive
    //Con dicho metodo vamos a buscar y devolver la metadata como respuesta (200, 404, etc.).
    @Override
    @Transactional(readOnly = true) 
    public ResponseEntity<CategoryResponseRest> search() {
        
        //Instanciamos un objeto de la clase CategoryResponseRest
        CategoryResponseRest response = new CategoryResponseRest();

        try {
            
            //Guardamos en un List las categorias que fueron encontradas
            List<Category> category = (List<Category>) categoryDao.findAll();

            //settear la lista con todas las categorias obtenidas en la respuesta 
            response.getCategoryResponse().setCategory(category);

            //setteamos el mensaje de exito 
            response.setMetadata("Respuesta Ok", "00", "Respuesta exitosa");

        } catch (Exception e) {

            response.setMetadata("Respuesta fallida", "-1", "Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
   
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

    }

    
    



}
