package com.oswi.inventory.inventarios.controllers;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oswi.inventory.inventarios.models.Product;
import com.oswi.inventory.inventarios.responses.ProductResponseRest;
import com.oswi.inventory.inventarios.services.IProductService;
import com.oswi.inventory.inventarios.util.ProductExcelExporter;
import com.oswi.inventory.inventarios.util.Util;

import jakarta.servlet.http.HttpServletResponse;

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


    /**
     * Buscar por name
     * @param name
     * @return
     */
    @GetMapping("/products/filter/{name}")
    public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name){
        ResponseEntity<ProductResponseRest> respponse = productService.searchByName(name);
        return respponse;
    }

    /**
     * Eliminar producto por id
     * @param id
     * @return
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> deleteById(@PathVariable Long id){
        ResponseEntity<ProductResponseRest> respponse = productService.deleteById(id);
        return respponse;
    }


    /**
     * Obtener el listado de productos
     * @return
     */
    @GetMapping("/products")
    public ResponseEntity<ProductResponseRest> search(){
        ResponseEntity<ProductResponseRest> respponse = productService.search();
        return respponse;
    }


    /**
     * Actualizar producto
     * @param picture
     * @param name
     * @param price
     * @param account
     * @param categoryID
     * @param id
     * @return
     * @throws IOException
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> update(
        @RequestParam("picture") MultipartFile picture,
        @RequestParam("name") String name,
        @RequestParam("price") int price,
        @RequestParam("account") int account,
        @RequestParam("categoryId") Long categoryID,
        @PathVariable Long id
        ) throws IOException {

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setAccount(account);
            //Comprimir la foto para poder guardarla en la bd
            product.setPicture(Util.compressZLib(picture.getBytes()));

            ResponseEntity<ProductResponseRest> respponse = productService.update(product, categoryID, id);

            return respponse;
    }

    /**
     * Exportar archivo excel con los productos
     * @param response
     * @throws IOException
     */
    @GetMapping("/products/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        //Esto representa un archivo excel
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_productos.xlsx";
        response.setHeader(headerKey, headerValue);

        ResponseEntity<ProductResponseRest> products = productService.search();

        ProductExcelExporter excelExporter = new ProductExcelExporter(
                                //Obtener la lista de productos
                                products.getBody().getProduct().getProducts()
                                );

        excelExporter.export(response);

    }


}
