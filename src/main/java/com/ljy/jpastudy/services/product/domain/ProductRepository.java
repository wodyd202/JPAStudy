package com.ljy.jpastudy.services.product.domain;

import com.ljy.jpastudy.services.product.application.model.ProductRecord;
import com.ljy.jpastudy.services.product.application.model.ProductSearchCondition;

import java.util.List;

public interface ProductRepository {
    List<ProductRecord> findAll(ProductSearchCondition condition);
    void save(Product product);
}
