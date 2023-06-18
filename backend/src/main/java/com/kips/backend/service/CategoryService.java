package com.kips.backend.service;

import com.kips.backend.service.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getById(Integer id);
    List<CategoryDto> getCategories();

    void create(CategoryDto categoryDto);
}
