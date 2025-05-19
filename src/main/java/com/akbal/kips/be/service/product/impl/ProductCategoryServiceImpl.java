package com.akbal.kips.be.service.product.impl;

import com.akbal.kips.be.constants.CacheNames;
import com.akbal.kips.be.dto.product.response.CategoryResponse;
import com.akbal.kips.be.repository.ProductCategoryRepository;
import com.akbal.kips.be.service.product.ProductCategoryService;
import com.akbal.kips.be.service.product.mapper.ProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductCategoryServiceImpl is an implementation of the ProductCategoryService interface.
 */

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    @Cacheable(CacheNames.ALL_CATEGORIES)
    public List<CategoryResponse> getAllCategories() {
        var categories = productCategoryRepository.findAllByParentIsNull();

        if (categories.isEmpty()) {
            return List.of();
        }
        return productCategoryMapper.toResponse(categories, true);
    }
}
