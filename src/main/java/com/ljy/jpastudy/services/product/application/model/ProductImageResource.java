package com.ljy.jpastudy.services.product.application.model;

import com.ljy.jpastudy.services.product.domain.ProductImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductImageResource {
    private long id;
    private String imagePath;
    private int ordering;
    private long productId;


    public static ProductImageResource from(ProductImageRecord productImage){
        return new ProductImageResource(productImage.getId(),
                                        productImage.getImagePath(),
                                        productImage.getOrdering(),
                                        productImage.getProductId());
    }

    public static ProductImageResource of(ProductImage productImage, Long productId) {
        return new ProductImageResource(productImage.getId(),
                                        productImage.getImagePath(),
                                        productImage.getOrdering(),
                                        productId);
    }
}
