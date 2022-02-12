package com.ljy.jpastudy.services.product.application.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {
    private String name;
    private long price;
    private List<ProductImageDto> productImages;

    public static ProductDto of(String name, long price, List<ProductImageDto> productImages){
        return new ProductDto(name, price, productImages);
    }
}