package com.oswi.inventory.inventarios.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oswi.inventory.inventarios.dao.ICategoryDao;
import com.oswi.inventory.inventarios.dao.IProductDao;
import com.oswi.inventory.inventarios.models.Category;
import com.oswi.inventory.inventarios.models.Product;
import com.oswi.inventory.inventarios.responses.ProductResponseRest;
//import com.oswi.inventory.inventarios.responses.ResponseRest;

@Service //Definimos esta clase como servicio
public class ProductServiceImpl implements IProductService {

    //Esta es otra forma de hacer inyeccion de dependencias, mediante constructor
    private ICategoryDao categoryDao;
    private IProductDao productDao;

    public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
        super();
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }
    
    @Override
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
        
        //Objeto de respuesta del servicio
        ProductResponseRest response = new ProductResponseRest();
        //Aqui se almacena la respuesta al llamar al metodo save
        List<Product> list = new ArrayList<>();

        try {
            
            //Buscar categoria a enlazar en el objeto de product
            Optional<Category> category = categoryDao.findById(categoryId);

            if(category.isPresent()) {
                product.setCategory(category.get());
            } else {
                response.setMetadata("Respuesta no ok", "404", "Categoria asociada al producto no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            //Guardar el producto
            Product productSaved = productDao.save(product);

            if(productSaved != null) {
                list.add(productSaved);
                response.getProduct().setProducts(list);
                response.setMetadata("respuesta ok", "200", "Producto Guardado");
            } else {
                response.setMetadata("Respuesta no ok", "400", "Producto no guardado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "500", "Error al guardar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);    

        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK); 

    }

}
