package com.kips.product.api.service.product.mapper;

import com.kips.product.api.domain.*;
import com.kips.product.api.dto.request.ProductCreateRequest;
import com.kips.product.api.dto.request.ProductUpdateRequest;
import com.kips.product.api.dto.response.AttributeResponse;
import com.kips.product.api.dto.response.ImageResponse;
import com.kips.product.api.dto.response.ProductDetailResponse;
import com.kips.product.api.dto.response.ProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    @Value("${file.download-url:''}")
    private String downloadUrl;

    public ProductEntity toEntity(ProductCreateRequest request) {
        var entity = new ProductEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(BigDecimal.valueOf(request.getPrice()));
        entity.setStockInCount(request.getStock());
        entity.setCategory(new CategoryEntity(request.getCategoryId()));
        entity.setBrand(new BrandEntity(request.getBrandId()));
        entity.setRating(0F);
        entity.setActive(true);
        entity.setDeleted(false);
        return entity;
    }

    public ProductEntity updateToEntity(ProductEntity entity, ProductUpdateRequest request) {
        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            entity.setPrice(BigDecimal.valueOf(request.getPrice()));
        }
        if (request.getStock() != null) {
            entity.setStockInCount(request.getStock());
        }
        if (request.getCategoryId() != null) {
            entity.setCategory(new CategoryEntity(request.getCategoryId()));
        }
        if (request.getBrandId() != null) {
            entity.setBrand(new BrandEntity(request.getBrandId()));
        }
        if (entity.getActive() != null) {
            entity.setActive(request.getActive());
        }
        return entity;
    }

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
                .images(toImageResponse(entity.getImages()))
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
        productDetailResponse.setImages(toImageResponse(entity.getImages()));
        return productDetailResponse;
    }

    private Set<ImageResponse> toImageResponse(Set<ProductImage> images) {
        if (images == null || images.isEmpty()) {
            return Set.of();

        }
        return images.stream()
                .map(p->ImageResponse.builder()
                        .order(p.getOrderNum())
                        .url(String.format("%s/product/%s", downloadUrl, p.getName()))
                        .isMain(p.getIsDefault())
                        .build())
                .collect(Collectors.toSet());
    }

    private AttributeResponse toAttributeResponse(ProductAttributeEntity attribute) {
        return AttributeResponse.builder()
                .name(attribute.getName())
                .value(attribute.getValue())
                .build();
    }
}
