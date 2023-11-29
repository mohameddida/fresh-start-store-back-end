package com.freshstratstore.freshstartstore.web.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductsForm {
    private String code;
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private String image;
}
