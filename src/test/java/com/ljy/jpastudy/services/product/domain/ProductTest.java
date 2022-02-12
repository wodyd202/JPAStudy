package com.ljy.jpastudy.services.product.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    void 상품이미지는_최소_하나이상_등록해야함(){
        // given
        List<ProductImage> emptyList = Collections.EMPTY_LIST;

        // when
        assertThrows(IllegalArgumentException.class,()->{
            ProductImages.listOf(emptyList);
        });
    }

    @Test
    void 상품이미지는_순서에_맞게_입력해야함(){
        // given
        List<ProductImage> emptyList = asList(
                ProductImage.of("path", 0),
                ProductImage.of("path", 0));

        // when
        assertThrows(IllegalArgumentException.class,()->{
            ProductImages.listOf(emptyList);
        });
    }

    @Test
    void 상품이미지_정상_등록(){
        // given
        List<ProductImage> emptyList = asList(
                ProductImage.of("path", 0),
                ProductImage.of("path", 1));

        // when
        ProductImages productImages = ProductImages.listOf(emptyList);

        // then
        assertEquals(2, productImages.totalCount());
    }
}
