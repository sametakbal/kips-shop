package com.kips.product.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryCreateRequest {
    private String name;
    private Long parentId;
}
