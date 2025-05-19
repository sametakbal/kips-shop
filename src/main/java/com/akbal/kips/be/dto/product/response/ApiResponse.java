package com.akbal.kips.be.dto.product.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse<T> {
    private T data;
    private boolean success;
    private HttpStatus status;
    private String message;
}
