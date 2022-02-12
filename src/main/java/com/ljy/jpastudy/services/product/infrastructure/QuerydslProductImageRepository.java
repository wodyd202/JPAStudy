package com.ljy.jpastudy.services.product.infrastructure;

import com.ljy.jpastudy.services.product.application.model.ProductImageRecord;
import com.ljy.jpastudy.services.product.domain.ProductImageRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.ljy.jpastudy.services.product.domain.QProductImage.productImage;
import static com.querydsl.core.types.Projections.constructor;

@Repository
@RequiredArgsConstructor
public class QuerydslProductImageRepository implements ProductImageRepository  {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductImageRecord> findAllByProductIds(Set<Long> productIds) {
        return jpaQueryFactory.select(constructor(ProductImageRecord.class,
                        productImage.id,
                        productImage.imagePath,
                        productImage.ordering,
                        productImage.product().id
                    ))
                .from(productImage)
                .where(productImage.product().id.in(productIds))
                .fetch();
    }
}
