package com.example.demo.global;

import org.springframework.http.HttpStatus;

public class MikException extends RuntimeException{
    private ErrorCode errorCode;
    private HttpStatus httpStatus;
    public MikException(ErrorCode errorCode,HttpStatus httpStatus){
        this.errorCode = errorCode;
        this.httpStatus =httpStatus;
    }
}
