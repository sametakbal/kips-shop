package com.akbal.kips.be.dto.product.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryRequest {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    private Long parentId;
    private List<CategoryRequest> subCategories;
}
