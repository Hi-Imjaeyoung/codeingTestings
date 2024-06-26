package com.example.demo.Item.service;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.entity.Item;
import com.example.demo.Item.repository.ItemRepository;
import com.example.demo.global.ErrorCode;
import com.example.demo.global.MikException;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;

    public ItemResponseDto itemCreate(Long memberId, ItemRequestDto itemRequestDto){
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()-> new MikException(ErrorCode.NOT_FIND_MEMBER)
        );
        Item item = Item.toEntity(itemRequestDto,member);
        Item result = itemRepository.save(item);
        return result.toDto();
    }
}
