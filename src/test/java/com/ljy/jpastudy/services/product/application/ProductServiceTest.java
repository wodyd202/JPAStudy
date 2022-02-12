package com.ljy.jpastudy.services.product.application;

import com.ljy.jpastudy.services.product.application.model.ProductDto;
import com.ljy.jpastudy.services.product.application.model.ProductImageDto;
import com.ljy.jpastudy.services.product.application.model.ProductResource;
import com.ljy.jpastudy.services.product.application.model.ProductSearchDto;
import com.ljy.jpastudy.services.product.domain.ProductImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductServiceTest {
    @Autowired ProductService productService;
    @Autowired
    ProductImageRepository productImageRepository;

    @Test
    void 상품_등록(){
        // given
        ProductDto productDto = ProductDto.of("상품명", 1000, asList(ProductImageDto.of("path", 0)));

        // when
        ProductResource productResource = productService.create(productDto);

        // then
        assertNotNull(productResource);
    }

    @Test
    void 상품_리스트_조회(){
        // given
        ProductDto productDto = ProductDto.of("상품명", 1000, asList(ProductImageDto.of("path", 0)));
        productService.create(productDto);
        productService.create(productDto);

        ProductSearchDto productSearchDto = ProductSearchDto.of(0, 10);

        // when
        List<ProductResource> products = productService.getProducts(productSearchDto);

        // then
        assertNotNull(products);
    }
}
