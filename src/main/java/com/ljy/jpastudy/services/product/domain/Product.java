package com.ljy.jpastudy.services.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long price;

    @Getter(AccessLevel.NONE)
    @Embedded
    private ProductImages productImages;

    private Product(String name, long price, List<ProductImage> productImages) {
        this.name = name;
        this.price = price;
        setProductImages(productImages);
    }

    public static Product of(String name, long price, List<ProductImage> productImages){
        return new Product(name,price, productImages);
    }

    private void setProductImages(List<ProductImage> productImages){
        productImages.forEach(productImage -> productImage.setProduct(this));
        this.productImages = ProductImages.listOf(productImages);
    }

    public List<ProductImage> getProductImages(){
        return productImages.getProductImages();
    }
}
