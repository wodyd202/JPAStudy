package com.ljy.jpastudy.services.product.domain;

import com.ljy.jpastudy.services.product.application.model.ProductRecord;
import com.ljy.jpastudy.services.product.application.model.ProductSearchDto;

import java.util.List;

public interface ProductRepository {
    List<ProductRecord> findAll(ProductSearchDto productSearchDto);
    void save(Product product);
}
