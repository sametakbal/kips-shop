package com.kips.product.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItem {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
