package com.kips.product.api.service.brand.mapper;

import com.kips.product.api.domain.BrandEntity;
import com.kips.product.api.dto.request.BrandCreateOrUpdateRequest;
import com.kips.product.api.dto.response.BrandResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandMapper {
    public List<BrandResponse> toResponseList(List<BrandEntity> brandEntities) {
        if (brandEntities.isEmpty()) {
            return List.of();
        } else {
            return brandEntities.stream()
                    .map(this::toResponse)
                    .toList();
        }
    }

    private BrandResponse toResponse(BrandEntity brandEntity) {
        return BrandResponse.builder()
                .id(brandEntity.getId())
                .name(brandEntity.getName())
                .build();
    }

    public BrandEntity toEntity(BrandCreateOrUpdateRequest request) {
        var brandEntity = new BrandEntity();
        brandEntity.setId(request.getId());
        brandEntity.setName(request.getName());
        return brandEntity;
    }
}
