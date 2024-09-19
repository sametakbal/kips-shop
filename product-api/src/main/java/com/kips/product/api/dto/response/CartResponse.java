package com.kips.product.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
public class CartResponse {
    private Set<CartItem> items;
    private BigDecimal totalPrice;
}
