package com.akbal.kips.be.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryCreateRequest {
    @Valid
    @NotEmpty
    private List<CategoryRequest> categories;
}
