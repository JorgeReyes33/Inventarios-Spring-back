package com.oswi.inventory.inventarios.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseRest extends ResponseRest{

    private ProductResponse product = new ProductResponse();

}
