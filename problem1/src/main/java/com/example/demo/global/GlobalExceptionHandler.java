package com.example.demo.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MickException.class)
    public ResponseEntity<?> mickExceptionHandler(MickException e){
        ErrorCode errorCode = e.getErrorCode();
        String code = errorCode.getCode();;
        String message = errorCode.getMessage();
        HttpStatus httpStatus = errorCode.getStatus();
        return new ResponseEntity<>(
                message,httpStatus
        );
    }
}
