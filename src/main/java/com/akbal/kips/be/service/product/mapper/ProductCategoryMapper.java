package com.akbal.kips.be.service.product.mapper;

import com.akbal.kips.be.domain.product.ProductCategory;
import com.akbal.kips.be.dto.product.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryMapper {

    public CategoryResponse toResponse(ProductCategory entity, boolean includeChildren) {
        var response = CategoryResponse.builder()
                .id(entity.getId()).name(entity.getName()).build();

        if (!entity.getChildren().isEmpty() && includeChildren) {
            response.setChildren(entity.getChildren().stream()
                    .map(child -> toResponse(child, true))
                    .toList());
        }
        return response;
    }

    public List<CategoryResponse> toResponse(List<ProductCategory> entities, boolean includeChildren) {
        return entities.stream()
                .map(entity -> toResponse(entity, includeChildren))
                .toList();
    }
}
