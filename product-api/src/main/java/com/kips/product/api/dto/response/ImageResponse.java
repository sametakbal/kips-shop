package com.kips.product.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageResponse {
    private Integer order;
    private String url;
    private Boolean isMain;
}
