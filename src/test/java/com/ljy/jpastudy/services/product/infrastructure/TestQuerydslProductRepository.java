package com.ljy.jpastudy.services.product.infrastructure;

import com.ljy.jpastudy.core.querydsl.QuerydslSupport;
import com.ljy.jpastudy.services.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ljy.jpastudy.services.product.domain.QProduct.product;
import static com.ljy.jpastudy.services.product.domain.QReview.review;

@Repository
public class TestQuerydslProductRepository extends QuerydslSupport<Product> {
    public TestQuerydslProductRepository() { super(Product.class); }

    public List<Product> findAll() {
        return from(product)
        .select(product)
        .leftJoin(product.reviews, review)
        .fetchJoin()
        .fetch();
    }
}
