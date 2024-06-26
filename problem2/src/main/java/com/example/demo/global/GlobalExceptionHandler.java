package com.example.demo.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MikException.class)
    public ResponseEntity<Object> mikException(MikException e){
        ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(errorCode.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
