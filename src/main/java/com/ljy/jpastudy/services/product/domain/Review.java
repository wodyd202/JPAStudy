package com.ljy.jpastudy.services.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    private Long id;

    @Column(name = "user_id")
    private String reviewer;

    private String content;

    @ManyToOne
    private Product product;
}
