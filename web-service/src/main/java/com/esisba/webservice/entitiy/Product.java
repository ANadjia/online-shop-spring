package com.esisba.webservice.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Product {

    private Long id;

    private String productName;

    private String ref;

    private String manufacturer;

    private float price;

    private int qte;

    public Product(String productName, String ref, String manufacturer, float price, int qte) {
        this.productName = productName;
        this.ref = ref;
        this.manufacturer = manufacturer;
        this.price = price;
        this.qte = qte;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getRef() {
        return ref;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public float getPrice() {
        return price;
    }

    public int getQte() {
        return qte;
    }
}
