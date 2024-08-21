package com.kips.product.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CategoryCreateRequest {
    @Valid
    @NotEmpty
    @NotNull
    private List<CategoryRequest> categories;
}
