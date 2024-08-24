package com.kips.product.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttributeResponse {
    private String name;
    private String value;
}
