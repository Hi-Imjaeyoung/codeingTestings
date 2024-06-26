package com.example.demo.member.service;

import com.example.demo.member.dto.MemberRequestDto;
import com.example.demo.member.dto.MemberResponseDto;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public MemberResponseDto memberCreate(MemberRequestDto memberRequestDto){
        Member member = Member.toEntity(memberRequestDto);
        Member result = memberRepository.save(member);
        return result.toDto();
    }
}
