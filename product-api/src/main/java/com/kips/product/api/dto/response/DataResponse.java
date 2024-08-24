package com.kips.product.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> {
    private List<T> items = List.of();
    private Long totalCount = null;
}
