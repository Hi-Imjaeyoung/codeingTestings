package com.example.demo.global;

public enum ErrorCode {
    NOT_FOUND_MEMBER("C1","해당 멤버가 없습니다."),
    NOT_FOUND_ITEM("C2","해당 아이템이 없습니다."),
    NOT_ENOUGH_STOCK("C3","아이템 재고가 부족합니다");
    ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    };
    private String code;
    private String message;
}
