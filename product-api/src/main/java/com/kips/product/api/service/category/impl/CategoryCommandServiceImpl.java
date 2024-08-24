package com.kips.product.api.service.category.impl;

import com.kips.product.api.domain.CategoryEntity;
import com.kips.product.api.dto.request.CategoryCreateRequest;
import com.kips.product.api.dto.request.CategoryRequest;
import com.kips.product.api.repository.CategoryRepository;
import com.kips.product.api.service.category.CategoryCommandService;
import com.kips.product.api.service.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public void createCategory(CategoryCreateRequest request) {
        if (request == null || request.getCategories() == null) {
            throw new IllegalArgumentException("CategoryCreateRequest or categories list must not be null");
        }

        for (CategoryRequest categoryRequest : request.getCategories()) {
            createOrUpdateCategory(categoryRequest, null);
        }
    }

    private void createOrUpdateCategory(CategoryRequest categoryRequest, Long parentId) {
        CategoryEntity category = mapper.toEntity(categoryRequest, parentId);

        repository.save(category);

        if (categoryRequest.getSubCategories() != null) {
            for (CategoryRequest subCategoryRequest : categoryRequest.getSubCategories()) {
                createOrUpdateCategory(subCategoryRequest, category.getId());
            }
        }
    }

    @Override
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}
