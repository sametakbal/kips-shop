package com.kips.product.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandCreateOrUpdateRequest {
    @Positive
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
}
