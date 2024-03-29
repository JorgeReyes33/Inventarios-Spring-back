package com.oswi.inventory.inventarios.dao;

import org.springframework.data.repository.CrudRepository;

import com.oswi.inventory.inventarios.models.Category;

/*
 * Aqui estan mis repositorios
 * Esta interfaz nos permite acceder a todos los datos, nos proporciona ciertos metodos por defecto
 * como eliminar, buscar, etc.
 * dao = data access object
 * Diferencias entre jpa y CrudRepository
*/
public interface ICategoryDao extends CrudRepository<Category, Long>{

}
