package com.example.demo.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FIND_MEMBER("E1", HttpStatus.BAD_REQUEST,"해당 멤버를 찾을 수 없습니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(String code,HttpStatus status,String message){
        this.code = code;
        this.status =status;
        this.message =message;
    }
}
