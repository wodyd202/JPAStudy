package com.ljy.jpastudy.services.product.domain;

import com.ljy.jpastudy.services.product.application.model.ProductImageRecord;

import java.util.List;
import java.util.Set;

public interface ProductImageRepository {
    List<ProductImageRecord> findAllByProductIds(Set<Long> productIds);
}
