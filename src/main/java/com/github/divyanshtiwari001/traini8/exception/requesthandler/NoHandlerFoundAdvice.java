package com.github.divyanshtiwari001.traini8.exception.requesthandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.github.divyanshtiwari001.traini8.response.ErrorResponse;

@RestControllerAdvice
public class NoHandlerFoundAdvice {
    
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorResponse> noHandlerFoundHandler(NoHandlerFoundException ex) {
        ErrorResponse response = new ErrorResponse("Resource not found", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
