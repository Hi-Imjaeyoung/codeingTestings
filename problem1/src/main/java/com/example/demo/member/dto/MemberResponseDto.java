package com.example.demo.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    public MemberResponseDto(Long id,String email,String name){
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
