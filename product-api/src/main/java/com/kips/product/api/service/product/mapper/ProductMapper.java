package com.kips.product.api.service.product.mapper;

import com.kips.product.api.domain.ProductAttributeEntity;
import com.kips.product.api.domain.ProductEntity;
import com.kips.product.api.dto.response.AttributeResponse;
import com.kips.product.api.dto.response.ProductDetailResponse;
import com.kips.product.api.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductResponse toResponse(ProductEntity entity) {
        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .rating(entity.getRating())
                .categoryId(entity.getCategory().getId())
                .categoryName(entity.getCategory().getName())
                .brandId(entity.getBrand().getId())
                .brandName(entity.getBrand().getName())
                .price(entity.getPrice())
                .stockInCount(entity.getStockInCount())
                .build();
    }

    public ProductDetailResponse toDetailResponse(ProductEntity entity) {
        var productDetailResponse = new ProductDetailResponse();
        productDetailResponse.setId(entity.getId());
        productDetailResponse.setName(entity.getName());
        productDetailResponse.setRating(entity.getRating());
        productDetailResponse.setCategoryId(entity.getCategory().getId());
        productDetailResponse.setCategoryName(entity.getCategory().getName());
        productDetailResponse.setBrandId(entity.getBrand().getId());
        productDetailResponse.setBrandName(entity.getBrand().getName());
        productDetailResponse.setPrice(entity.getPrice());
        productDetailResponse.setStockInCount(entity.getStockInCount());
        productDetailResponse.setAttributes(entity.getAttributes().stream().map(this::toAttributeResponse).toList());
        productDetailResponse.setDescription(entity.getDescription());
        return productDetailResponse;
    }

    private AttributeResponse toAttributeResponse(ProductAttributeEntity attribute) {
        return AttributeResponse.builder()
                .name(attribute.getName())
                .value(attribute.getValue())
                .build();
    }
}
