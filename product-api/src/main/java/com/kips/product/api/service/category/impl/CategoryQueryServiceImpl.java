package com.kips.product.api.service.category.impl;

import com.kips.product.api.dto.response.CategoryResponse;
import com.kips.product.api.repository.CategoryRepository;
import com.kips.product.api.service.category.CategoryQueryService;
import com.kips.product.api.service.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getCategories() {
        var categories = categoryRepository.findAllByParentIsNull();
        return categoryMapper.toResponse(categories);
    }
}
