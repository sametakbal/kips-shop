package com.kips.product.api.service.category.mapper;

import com.kips.product.api.domain.CategoryEntity;
import com.kips.product.api.dto.request.CategoryCreateRequest;
import com.kips.product.api.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMapper {

    public CategoryEntity toEntity(CategoryCreateRequest request) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(request.getName());
        if (request.getParentId() != null) {
            entity.setParent(new CategoryEntity(request.getParentId()));
        }
        return entity;
    }

    public CategoryResponse toResponse(CategoryEntity entity) {
        var response = CategoryResponse.builder()
                .id(entity.getId()).name(entity.getName()).build();

        if (!entity.getChildren().isEmpty()) {
            response.setChildren(entity.getChildren().stream()
                    .map(this::toResponse)
                    .toList());
        }
        return response;
    }

    public List<CategoryResponse> toResponse(List<CategoryEntity> entities) {
        return entities.stream()
                .map(this::toResponse)
                .toList();
    }
}
