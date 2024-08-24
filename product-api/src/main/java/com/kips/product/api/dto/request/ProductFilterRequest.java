package com.kips.product.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterRequest {
    private int page;
    private int size;
    private String name;
    private String description;
    private Float rating;
    private Boolean inStock;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Long categoryId;
    private Long brandId;
    private String sortBy;
    private String sortOrder;
}
