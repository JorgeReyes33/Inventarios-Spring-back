package com.oswi.inventory.inventarios.responses;

import java.util.List;

import com.oswi.inventory.inventarios.models.Product;

import lombok.Data;

@Data
public class ProductResponse {

    List<Product> products;

}
