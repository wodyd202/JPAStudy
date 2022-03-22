package com.ljy.jpastudy.services.product.application;

import com.ljy.jpastudy.services.product.application.model.*;
import com.ljy.jpastudy.services.product.domain.Product;
import com.ljy.jpastudy.services.product.domain.ProductImage;
import com.ljy.jpastudy.services.product.domain.ProductImageRepository;
import com.ljy.jpastudy.services.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Transactional
    public ProductResource create(ProductDto productDto) {
        List<ProductImage> productImages = productDto.getProductImages().stream()
                                        .map(productImage -> ProductImage.of(productImage.getImagePath(), productImage.getOrdering()))
                                        .collect(toList());
        Product product = Product.of(productDto.getName(), productDto.getPrice(), productImages);
        productRepository.save(product);
        return ProductResource.from(product);
    }

    private static final List<ProductResource> EMPTY_PRODUCT_RESOURCE_LIST = Collections.EMPTY_LIST;
    @Transactional(readOnly = true)
    public List<ProductResource> getProducts(ProductSearchCondition productSearchDto) {
        List<ProductRecord> products = productRepository.findAll(productSearchDto);
        Set<Long> productIds = products.stream()
                .map(ProductRecord::getId)
                .collect(toSet());
        if(productIds.isEmpty()){
            return EMPTY_PRODUCT_RESOURCE_LIST;
        }
        Map<Long, List<ProductImageResource>> productImageMap = loadProductImageRecordMapByProductIds(productIds);
        return products.stream()
                .map(product -> ProductResource.of(product, productImageMap.get(product.getId())))
                .collect(toList());
    }

    private Map<Long, List<ProductImageResource>> loadProductImageRecordMapByProductIds(Set<Long> productIds) {
        List<ProductImageRecord> productImages = productImageRepository.findAllByProductIds(productIds);
        return productImages.stream()
                .map(ProductImageResource::from)
                .collect(groupingBy(ProductImageResource::getProductId));
    }
}
