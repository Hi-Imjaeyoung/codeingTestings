package com.example.demo.global;

import lombok.Getter;

@Getter
public class MikException extends RuntimeException{
    private ErrorCode errorCode;

    public MikException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
