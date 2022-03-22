package com.ljy.jpastudy.services.product.infrastructure;

import com.ljy.jpastudy.services.product.domain.Product;
import com.ljy.jpastudy.services.product.domain.ProductImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static java.util.Arrays.asList;

@DataJpaTest
@Import(TestQuerydslProductRepository.class)
class ProductRepositoryTest {

    @Autowired
    TestProductJpaRepository productRepository;

    @Test
    void N_1_문제_테스트() {
        // given
        Product product_1 = Product.of("product_1", 30000, asList(ProductImage.of("imagePath", 0)));
        Product product_2 = Product.of("product_2", 20000, asList(ProductImage.of("imagePath", 0)));
        productRepository.save(product_1);
        productRepository.save(product_2);

        // when
        productRepository.findAll();
    }
}
