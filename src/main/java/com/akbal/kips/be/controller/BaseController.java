package com.akbal.kips.be.controller;

import com.akbal.kips.be.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;

public class BaseController {

    public <T> ApiResponse<T> respond(T item) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(item)
                .status(HttpStatus.OK)
                .build();
    }
}
