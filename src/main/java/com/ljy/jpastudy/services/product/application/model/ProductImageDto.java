package com.ljy.jpastudy.services.product.application.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductImageDto {
    private String imagePath;
    private int ordering;

    public static ProductImageDto of(String imagePath, int ordering){
        return new ProductImageDto(imagePath, ordering);
    }
}
