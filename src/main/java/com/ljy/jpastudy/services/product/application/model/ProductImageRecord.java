package com.ljy.jpastudy.services.product.application.model;

import lombok.Getter;

@Getter
public class ProductImageRecord {
    private long id;
    private String imagePath;
    private int ordering;
    private long productId;

    public ProductImageRecord(long id, String imagePath, int ordering, long productId) {
        this.id = id;
        this.imagePath = imagePath;
        this.ordering = ordering;
        this.productId = productId;
    }
}
