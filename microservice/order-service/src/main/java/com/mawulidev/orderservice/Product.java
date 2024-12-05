package com.mawulidev.orderservice;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class Product {
    private Long id;
    private String name;
    private double price;
    private String description;
    private int quantity;

//    public Product(Long id, String name, String description, int quantity) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.quantity = quantity;
//    }

}
