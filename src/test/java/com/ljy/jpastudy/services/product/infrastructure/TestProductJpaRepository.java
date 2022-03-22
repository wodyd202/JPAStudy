package com.ljy.jpastudy.services.product.infrastructure;

import com.ljy.jpastudy.services.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestProductJpaRepository extends JpaRepository<Product, Long> {
}
