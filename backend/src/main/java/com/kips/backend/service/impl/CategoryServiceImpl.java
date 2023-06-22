package com.kips.backend.service.impl;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.common.util.ValidationUtil;
import com.kips.backend.domain.Category;
import com.kips.backend.repository.CategoryRepository;
import com.kips.backend.service.CategoryService;
import com.kips.backend.service.dto.CategoryDto;
import com.kips.backend.service.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto getById(Integer id) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return mapper.toDto(category);
        }
        throw new GeneralException(String.format("Category id %s not found", id));
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categoryList = repository.findAll();
        return mapper.toDtoList(categoryList);
    }

    @Override
    public void create(CategoryDto categoryDto) {
        ValidationUtil.fieldCheckNullOrEmpty(categoryDto.getName(),"Name");
        ValidationUtil.fieldCheckNullOrEmpty(categoryDto.getSlug(),"Slug");
        Category category = mapper.toEntity(categoryDto);
        repository.save(category);

    }
}
