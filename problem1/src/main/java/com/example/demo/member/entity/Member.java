package com.example.demo.member.entity;

import com.example.demo.member.dto.MemberRequestDto;
import com.example.demo.member.dto.MemberResponseDto;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Builder
@Getter
public class Member {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
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
        return new MemberResponseDto(this.id.toString(),this.email,this.name);
    }
}
