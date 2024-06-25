package com.example.demo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private String id;
    private String name;
    private String email;
    public MemberResponseDto(String id,String email,String name){
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
