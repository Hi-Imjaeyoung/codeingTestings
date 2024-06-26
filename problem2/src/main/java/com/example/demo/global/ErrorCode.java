package com.example.demo.global;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FIND_MEMBER("E1","해당 멤버가 없습니다");
    ErrorCode(String code, String message){
        this.code = code;
        this.message =message;
    }
    private String code;
    private String message;
}
