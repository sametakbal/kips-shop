package com.kips.product.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductUpdateRequest extends ProductCreateRequest {
    @NotNull
    @Positive
    private Long id;

    private Boolean active;

    private Boolean deleted;
}
