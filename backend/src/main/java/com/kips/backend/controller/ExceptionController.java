package com.kips.backend.controller;

import com.kips.backend.common.exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<ErrorMessageResponse> exception(GeneralException exception) {
        return new ResponseEntity<>(new ErrorMessageResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ErrorMessageResponse{
    private String errorMessage;
}

