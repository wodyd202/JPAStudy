package com.ljy.jpastudy.services.product.application.model;

import lombok.Getter;

@Getter
public class ProductRecord {
    private long id;
    private String name;
    private long price;

    public ProductRecord(long id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
