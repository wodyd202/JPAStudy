package com.ljy.jpastudy.services.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

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

    @BatchSize(size = 10)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<Review> reviews;

    private Product(String name, long price, List<ProductImage> productImages) {
        this.name = name;
        this.price = price;
        setProductImages(productImages);
    }

    private void setProductImages(List<ProductImage> productImages){
        productImages.forEach(productImage -> productImage.setProduct(this));
        this.productImages = ProductImages.listOf(productImages);
    }

    public static Product of(String name, long price, List<ProductImage> productImages){
        return new Product(name,price, productImages);
    }

    public List<ProductImage> getProductImages(){
        return productImages.getProductImages();
    }
}
