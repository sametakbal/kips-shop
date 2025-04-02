package com.akbal.kips.be.service.category.mapper;

import com.akbal.kips.be.domain.CategoryEntity;
import com.akbal.kips.be.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMapper {

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
