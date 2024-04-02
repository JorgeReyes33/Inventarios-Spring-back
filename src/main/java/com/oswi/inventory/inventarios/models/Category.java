package com.oswi.inventory.inventarios.models;

import java.io.Serializable;

//import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
 * Esta clase es el modelo
 * Es decir, esta nos indica como es que va a lucir o como va a crearse la estructura 
 * de la tabla en la bd.
 * Aqui se declaran los atributos.
*/

@Data //Implementar todos los metodos get y constructores, con lombok
@Entity //Para mapear esta clase como una tabla en la BD
@Table(name = "categorias") //Definir el nombre que se le asignara en la base de datos
public class Category implements Serializable {
    
    private static final long serialVersionUID = -4310027227752446841L;

    @Id //Indicar el id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera el ID
    private Long id;
    private String name;
    private String description;

}
