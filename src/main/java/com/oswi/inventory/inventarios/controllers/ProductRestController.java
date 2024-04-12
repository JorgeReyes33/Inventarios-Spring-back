package com.oswi.inventory.inventarios.controllers;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oswi.inventory.inventarios.models.Product;
import com.oswi.inventory.inventarios.responses.ProductResponseRest;
import com.oswi.inventory.inventarios.services.IProductService;
import com.oswi.inventory.inventarios.util.Util;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController //Definir esta clase como controlador tipo rest
@RequestMapping("/api/v1") //Definir url base
public class ProductRestController {

    private IProductService productService;

    public ProductRestController(IProductService productService) {
        super();
        this.productService = productService;
    }

    /**
     * 
     * @param picture
     * @param name
     * @param price
     * @param account
     * @param categoryID
     * @return
     * @throws IOException
     */
    @PostMapping("/products")
    public ResponseEntity<ProductResponseRest> save(
        @RequestParam("picture") MultipartFile picture,
        @RequestParam("name") String name,
        @RequestParam("price") int price,
        @RequestParam("account") int account,
        @RequestParam("categoryId") Long categoryID
        ) throws IOException {

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setAccount(account);
            //Comprimir la foto para poder guardarla en la bd
            product.setPicture(Util.compressZLib(picture.getBytes()));

            ResponseEntity<ProductResponseRest> respponse = productService.save(product, categoryID);

            return respponse;
    }


    /**
     * Search by id
     * @param id
     * @return
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id){
        ResponseEntity<ProductResponseRest> respponse = productService.searchById(id);
        return respponse;
    }

}
