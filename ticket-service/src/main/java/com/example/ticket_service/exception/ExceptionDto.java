package com.example.ticket_service.exception;

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
