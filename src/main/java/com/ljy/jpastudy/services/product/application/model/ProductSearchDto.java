package com.ljy.jpastudy.services.product.application.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSearchDto {
    private int page;
    private int size;

    public static ProductSearchDto of(int page, int size){
        return new ProductSearchDto(page, size);
    }
}
