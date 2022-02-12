package com.ljy.jpastudy.services.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImages {
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImages;

    private ProductImages(List<ProductImage> productImages){
        verifyNotEmpty(productImages);
        verifyValidOrdering(productImages);
        this.productImages = productImages;
    }

    private void verifyNotEmpty(List<ProductImage> productImages){
        if(productImages.isEmpty()){
            throw new IllegalArgumentException();
        }
    }

    private void verifyValidOrdering(List<ProductImage> productImages) {
        boolean[] orderingCheckArr = new boolean[productImages.size()];
        int totalCount = productImages.size();
        for (ProductImage productImage : productImages) {
            int ordering = productImage.getOrdering();
            verifyValidOrdering(ordering, totalCount);
            orderingCheckArr[ordering] = true;
        }
        verifyValidOrderingCheckArr(orderingCheckArr);
    }

    private void verifyValidOrdering(int ordering, int productImagesCount){
        if (isInValidOrdering(ordering, productImagesCount)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isInValidOrdering(int ordering, int productImagesCount){
        return ordering < 0 || ordering >= productImagesCount;
    }

    private void verifyValidOrderingCheckArr(boolean[] orderingCheckArr){
        for (boolean validOrdering : orderingCheckArr) {
            if(!validOrdering){
                throw new IllegalArgumentException();
            }
        }
    }

    public static ProductImages listOf(List<ProductImage> productImages){
        return new ProductImages(productImages);
    }

    public int totalCount() {
        return productImages.size();
    }
}
