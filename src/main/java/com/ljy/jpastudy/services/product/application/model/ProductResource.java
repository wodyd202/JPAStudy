package com.ljy.jpastudy.services.product.application.model;

import com.ljy.jpastudy.services.product.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResource {
    private long id;
    private String name;
    private long price;
    private List<ProductImageResource> productImages;

    public static ProductResource from(Product product){
        List<ProductImageResource> productImages = product.getProductImages().stream().map(productImage -> ProductImageResource.of(productImage, product.getId())).collect(toList());
        return new ProductResource(product.getId(),
                                   product.getName(),
                                   product.getPrice(),
                                   productImages);
    }

    public static ProductResource of(ProductRecord product, List<ProductImageResource> productImages) {
        return new ProductResource(
                product.getId(),
                product.getName(),
                product.getPrice(),
                productImages
        );
    }
}
