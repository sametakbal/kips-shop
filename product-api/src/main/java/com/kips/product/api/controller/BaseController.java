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

    public <T> ResponseEntity<T> respond(T item) {
        return ResponseEntity.ok(item);
    }

    public <T> ResponseEntity<DataResponse<T>> respond(List<T> items,Long totalSize) {
        var response = new DataResponse<T>();
        response.setItems(items);
        response.setTotalCount(totalSize);
        return ResponseEntity.ok(response);
    }
}
