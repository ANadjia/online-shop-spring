package com.esisba.orderservice.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Product {

    private Long id;

    private String productName;

    private String ref;

    private String manufacturer;

    private float price;

    private int qte;
}
