package com.ljy.jpastudy.services.product.infrastructure;

import com.ljy.jpastudy.services.product.application.model.ProductRecord;
import com.ljy.jpastudy.services.product.domain.ProductRepository;
import com.ljy.jpastudy.services.product.application.model.ProductSearchDto;
import com.ljy.jpastudy.services.product.domain.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

import static com.ljy.jpastudy.services.product.domain.QProduct.product;
import static com.querydsl.core.types.Projections.constructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class QuerydslProductRepository implements ProductRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    private final static List<ProductRecord> EMPTY_PRODUCT_RECORD_LIST = Collections.EMPTY_LIST;
    @Override
    public List<ProductRecord> findAll(ProductSearchDto productSearchDto) {
        List<Long> productIds = jpaQueryFactory.select(product.id)
                .from(product)
                .limit(productSearchDto.getSize())
                .offset(productSearchDto.getSize() * productSearchDto.getPage())
                .fetch();
        if(productIds.isEmpty()){
            return EMPTY_PRODUCT_RECORD_LIST;
        }
        return jpaQueryFactory.select(constructor(ProductRecord.class,
                        product.id,
                        product.name,
                        product.price
                    ))
                .from(product)
                .where(product.id.in(productIds))
                .fetch();
    }

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }
}
