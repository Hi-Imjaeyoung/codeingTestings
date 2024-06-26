package com.example.demo.item.service;

import com.example.demo.item.dto.ItemRequestDto;
import com.example.demo.item.dto.ItemResponseDto;
import com.example.demo.item.entity.Item;
import com.example.demo.item.repository.ItemRepository;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RedisService redisService;
    public ItemResponseDto createItem(Long memberId, ItemRequestDto itemRequestDto){
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()-> new IllegalArgumentException("해당 멤버가 없습니다")
        );
        Item item = Item.toEntity(member,itemRequestDto);
        Item result = itemRepository.save(item);
        redisService.setItemQuantity(result.getId(),result.getStock());
        return result.toDto();
    }
}
