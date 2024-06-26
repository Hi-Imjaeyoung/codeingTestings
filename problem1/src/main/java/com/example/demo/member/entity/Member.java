package com.example.demo.member.entity;

import com.example.demo.member.dto.MemberRequestDto;
import com.example.demo.member.dto.MemberResponseDto;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;
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
        return new MemberResponseDto(this.memberId,this.email,this.name);
    }
}
