package com.example.demo.member.entity;

import com.example.demo.member.dto.MemberRequestDto;
import com.example.demo.member.dto.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;

    public static Member toEntity(MemberRequestDto memberRequestDto){
        return Member.builder()
                .email(memberRequestDto.getEmail())
                .name(memberRequestDto.getName())
                .build();
    }

    public MemberResponseDto toDto(){
        return new MemberResponseDto(this.id,this.email,this.name);
    }
}
