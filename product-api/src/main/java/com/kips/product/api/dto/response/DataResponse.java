package com.kips.product.api.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class DataResponse <T> {
    private List<T> items = List.of();
}
