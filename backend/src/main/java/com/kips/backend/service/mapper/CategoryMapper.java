package com.kips.backend.service.mapper;

import com.kips.backend.domain.Category;
import com.kips.backend.service.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMapper {

    public CategoryDto toDto(Category category){
        if (category == null) {
            return null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .parent(getOnlyCategory(category.getParent()))
                .children(getOnlyCategories(category.getChildren()))
                .build();
    }

    CategoryDto getOnlyCategory(Category category){
        if (category == null) {
            return null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .build();
    }

    List<CategoryDto> getOnlyCategories(List<Category> categories){
        return categories.stream().map(this::getOnlyCategory).toList();
    }

    public List<CategoryDto> toDtoList(List<Category> categories){
        return categories.stream().map(this::toDto).toList();
    }

    Category entityFromId(CategoryDto dto){
        if (dto == null) {
            return null;
        }
        return Category.builder().id(dto.getId()).build();
    }


    public Category toEntity(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .slug(categoryDto.getSlug())
                .parent(entityFromId(categoryDto.getParent()))
                .build();
    }
}
