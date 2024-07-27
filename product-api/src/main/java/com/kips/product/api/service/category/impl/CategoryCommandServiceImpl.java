package com.kips.product.api.service.category.impl;

import com.kips.product.api.dto.request.CategoryCreateRequest;
import com.kips.product.api.repository.CategoryRepository;
import com.kips.product.api.service.category.CategoryCommandService;
import com.kips.product.api.service.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createCategory(CategoryCreateRequest request) {
        categoryRepository.save(categoryMapper.toEntity(request));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
