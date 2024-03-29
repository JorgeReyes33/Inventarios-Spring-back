package com.oswi.inventory.inventarios.responses;

import java.util.List;

import com.oswi.inventory.inventarios.models.Category;

import lombok.Data;

@Data //Uso de lombok para los metodos get y set
public class CategoryResponse {
    
    private List<Category> category;

}
