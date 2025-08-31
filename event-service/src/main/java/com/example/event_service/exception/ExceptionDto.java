package com.example.event_service.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionDto {
    HttpStatus httpStatus;
    String errorCode;
    String exceptionMessage;

}
