package com.ljy.jpastudy.services.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int ordering;

    @Column(nullable = false)
    private String imagePath;

    @ManyToOne
    @Getter(AccessLevel.NONE)
    private Product product;

    void setProduct(Product product){
        this.product = product;
    }

    private ProductImage(String imagePath, int ordering){
        this.imagePath = imagePath;
        this.ordering = ordering;
    }

    public static ProductImage of(String imagePath, int ordering){
        return new ProductImage(imagePath, ordering);
    }

}
