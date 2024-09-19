package com.kips.product.api.service.product.impl;

import com.kips.product.api.domain.ProductEntity;
import com.kips.product.api.dto.request.AttributeRequest;
import com.kips.product.api.dto.request.ProductFilterRequest;
import com.kips.product.api.dto.response.ProductDetailResponse;
import com.kips.product.api.dto.response.ProductResponse;
import com.kips.product.api.repository.ProductRepository;
import com.kips.product.api.service.product.ProductQueryService;
import com.kips.product.api.service.product.mapper.ProductMapper;
import com.kips.product.api.service.product.specs.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    //@Cacheable(CacheNames.PRODUCTS)
    public Page<ProductResponse> getAllProducts(ProductFilterRequest filterRequest,
                                                AttributeRequest attributeRequest) {
        Specification<ProductEntity> spec = Specification.where(ProductSpecification.isActive())
                .and(ProductSpecification.isNotDeleted());

        if (filterRequest.getName() != null) {
            spec = spec.and(ProductSpecification.hasName(filterRequest.getName()));
        }
        if (filterRequest.getCategoryId() != null) {
            spec = spec.and(ProductSpecification.hasCategory(filterRequest.getCategoryId()));
        }
        if (filterRequest.getBrandId() != null) {
            spec = spec.and(ProductSpecification.hasBrand(filterRequest.getBrandId()));
        }
        if (filterRequest.getMinPrice() != null) {
            spec = spec.and(ProductSpecification.hasMinPrice(filterRequest.getMinPrice()));
        }

        if (filterRequest.getMaxPrice() != null) {
            spec = spec.and(ProductSpecification.hasMaxPrice(filterRequest.getMaxPrice()));
        }

        if (Boolean.TRUE.equals(filterRequest.getInStock())) {
            spec = spec.and(ProductSpecification.hasInStock());
        }

        if (attributeRequest != null && attributeRequest.getAttributes() != null) {
            Set<Map.Entry<String, String>> entries = attributeRequest.getAttributes().entrySet();
            for (Map.Entry<String, String> entry : entries) {
                spec = spec.and(ProductSpecification.hasAttribute(entry.getKey(), entry.getValue()));
            }
        }

        Sort.Direction direction = Sort.Direction.valueOf(filterRequest.getSortOrder());
        Sort sort = Sort.by(direction, filterRequest.getSortBy());
        PageRequest pageRequest = PageRequest.of(filterRequest.getPage(), filterRequest.getSize(), sort);

        return productRepository
                .findAll(spec, pageRequest)
                .map(productMapper::toResponse);
    }

    @Override
    public ProductDetailResponse getProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
        return productMapper.toDetailResponse(productEntity);
    }
}
