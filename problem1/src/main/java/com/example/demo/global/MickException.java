package com.example.demo.global;

import lombok.Getter;

@Getter
public class MickException extends RuntimeException{
    private final ErrorCode errorCode;

    public MickException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
