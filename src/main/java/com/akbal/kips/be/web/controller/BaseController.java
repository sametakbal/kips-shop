package com.akbal.kips.be.web.controller;

import com.akbal.kips.be.dto.product.response.ApiResponse;
import org.springframework.http.HttpStatus;

public class BaseController {

    public <T> ApiResponse<T> respond(T item) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(item)
                .status(HttpStatus.OK)
                .build();
    }

    public ApiResponse<Void> error(String message) {
        return ApiResponse.<Void>builder()
                .success(false)
                .message(message)
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
