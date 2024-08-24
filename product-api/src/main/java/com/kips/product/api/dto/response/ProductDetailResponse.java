package com.kips.product.api.dto.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductDetailResponse extends ProductResponse {
    private String description;
    private Integer stockInCount;
    private List<AttributeResponse> attributes;
}
