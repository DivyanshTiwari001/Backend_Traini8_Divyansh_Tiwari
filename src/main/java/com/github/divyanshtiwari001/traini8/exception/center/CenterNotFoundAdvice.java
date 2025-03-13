package com.github.divyanshtiwari001.traini8.exception.center;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CenterNotFoundAdvice {
    @ExceptionHandler(CenterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(CenterNotFoundException ex) {
        return ex.getMessage();
    }
}
