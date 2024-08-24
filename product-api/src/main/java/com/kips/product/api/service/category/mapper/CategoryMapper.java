package com.kips.product.api.service.category.mapper;

import com.kips.product.api.domain.CategoryEntity;
import com.kips.product.api.dto.request.CategoryRequest;
import com.kips.product.api.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMapper {

    public CategoryEntity toEntity(CategoryRequest request, Long parentId) {
        var entity = new CategoryEntity();
        entity.setName(request.getName());
        entity.setParent(parentId != null ? new CategoryEntity(parentId) : null);
        return entity;
    }

    public CategoryResponse toResponse(CategoryEntity entity, boolean includeChildren) {
        var response = CategoryResponse.builder()
                .id(entity.getId()).name(entity.getName()).build();

        if (!entity.getChildren().isEmpty() && includeChildren) {
            response.setChildren(entity.getChildren().stream()
                    .map(child -> toResponse(child, true))
                    .toList());
        }
        return response;
    }

    public List<CategoryResponse> toResponse(List<CategoryEntity> entities, boolean includeChildren) {
        return entities.stream()
                .map(entity -> toResponse(entity, includeChildren))
                .toList();
    }
}
