package com.akbal.kips.be.service.category.impl;

import com.akbal.kips.be.constants.CacheNames;
import com.akbal.kips.be.dto.response.CategoryResponse;
import com.akbal.kips.be.repository.CategoryRepository;
import com.akbal.kips.be.service.category.CategoryQueryService;
import com.akbal.kips.be.service.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryQueryServiceImpl is an implementation of the CategoryQueryService interface.
 * It provides methods to query categories from the database.
 */

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable(CacheNames.ALL_CATEGORIES)
    public List<CategoryResponse> getAllCategories() {
        var categories = categoryRepository.findAllByParentIsNull();

        if (categories.isEmpty()) {
            return List.of();
        }
        return categoryMapper.toResponse(categories, true);
    }
}
