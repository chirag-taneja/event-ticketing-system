package com.example.user_service.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionDto {
    HttpStatus httpStatus;
    String errorCode;
    String exceptionMessage;

}
