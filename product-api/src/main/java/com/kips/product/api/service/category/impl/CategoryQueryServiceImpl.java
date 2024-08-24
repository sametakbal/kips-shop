package com.kips.product.api.service.category.impl;

import com.kips.product.api.common.constants.CacheNames;
import com.kips.product.api.dto.response.CategoryResponse;
import com.kips.product.api.repository.CategoryRepository;
import com.kips.product.api.service.category.CategoryQueryService;
import com.kips.product.api.service.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Cacheable(CacheNames.ALL_CATEGORIES)
    public List<CategoryResponse> getAllCategories() {
        var categories = categoryRepository.findAllByParentIsNull();
        if (categories.isEmpty()) {
            return List.of();
        }
        return categoryMapper.toResponse(categories, true);
    }

    @Override
    @Cacheable(CacheNames.CATEGORIES_BY_PARENT)
    public List<CategoryResponse> getCategoriesByParent(Long parentId) {
        var allByParentId = categoryRepository.findAllByParentId(parentId);
        if (allByParentId.isEmpty()) {
            return List.of();
        }
        return categoryMapper.toResponse(allByParentId, true);
    }

    @Override
    @Cacheable(CacheNames.PARENT_CATEGORIES)
    public List<CategoryResponse> getCategories() {
        var categories = categoryRepository.findAllByParentIsNull();
        if (categories.isEmpty()) {
            log.warn("No parent categories found");
            return List.of();
        }
        return categoryMapper.toResponse(categories, false);
    }

}
