package com.kips.product.api.controller;

import com.kips.product.api.dto.response.DataResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseController {

    public <T> ResponseEntity<DataResponse<T>> respond(List<T> items) {
        var response = new DataResponse<T>();
        response.setItems(items);
        return ResponseEntity.ok(response);
    }
}
