package com.kips.product.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Float rating;
    private BigDecimal price;
    private Integer stockInCount;
    private Long categoryId;
    private String categoryName;
    private Long brandId;
    private String brandName;
    private Set<ImageResponse> images;
}
