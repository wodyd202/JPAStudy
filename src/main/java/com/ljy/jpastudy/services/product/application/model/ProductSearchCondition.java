package com.ljy.jpastudy.services.product.application.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSearchCondition {
    private int page;
    private int size;

    public static ProductSearchCondition of(int page, int size){
        return new ProductSearchCondition(page, size);
    }
}
