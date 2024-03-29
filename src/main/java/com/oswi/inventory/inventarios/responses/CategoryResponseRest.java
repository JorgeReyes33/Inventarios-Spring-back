package com.oswi.inventory.inventarios.responses;

import lombok.Getter;
import lombok.Setter;

//Aplicamos la herencia para tener todos los atributos de nuestra clase responseRest
@Getter
@Setter
public class CategoryResponseRest extends ResponseRest{
    
    //Instanciamos un objeto para guardar aqui la respuesta de las categorias
    private CategoryResponse categoryResponse = new CategoryResponse();

}
