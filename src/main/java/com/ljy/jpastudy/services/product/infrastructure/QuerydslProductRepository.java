package com.ljy.jpastudy.services.product.infrastructure;

import com.ljy.jpastudy.core.querydsl.QuerydslSupport;
import com.ljy.jpastudy.services.product.application.model.ProductRecord;
import com.ljy.jpastudy.services.product.application.model.ProductSearchCondition;
import com.ljy.jpastudy.services.product.domain.Product;
import com.ljy.jpastudy.services.product.domain.ProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.ljy.jpastudy.services.product.domain.QProduct.product;
import static com.querydsl.core.types.Projections.constructor;

@Repository
@Transactional
public class QuerydslProductRepository extends QuerydslSupport<Product> implements ProductRepository {
    protected QuerydslProductRepository() { super(Product.class); }

    private static final List<ProductRecord> EMPTY_PRODUCT_RECORD_LIST = Collections.EMPTY_LIST;

    @Override
    public List<ProductRecord> findAll(ProductSearchCondition condition) {
        List<Long> productIds =
                from(product)
                .select(product.id)
                .limit(condition.getSize())
                .offset(condition.getSize() * condition.getPage())
                .fetch();
        if(productIds.isEmpty()){
            return EMPTY_PRODUCT_RECORD_LIST;
        }
        return from(product)
                .select(constructor(ProductRecord.class,
                        product.id,
                        product.name,
                        product.price
                ))
                .where(product.id.in(productIds))
                .fetch();
    }
}
