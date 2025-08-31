package com.example.ticket_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> globalExceptionHandler(Exception e) {
        ExceptionDto exceptionDto = ExceptionDto.builder()
                .exceptionMessage(e.getMessage() != null ? e.getMessage() : "Unknown error")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorCode(e.getCause() != null ? e.getCause().toString() : "N/A")
                .build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

