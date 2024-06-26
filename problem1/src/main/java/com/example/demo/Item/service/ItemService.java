package com.example.demo.Item.service;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.entity.Item;
import com.example.demo.Item.repository.ItemRepository;
import com.example.demo.Item.repository.RecentItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RecentItemRepository recentItemRepository;

    public ItemResponseDto createItem(ItemRequestDto itemRequestDto){
        Item item = Item.toEntity(itemRequestDto);
        Item result = itemRepository.save(item);
        return result.toDto();
    }

    public ItemResponseDto showItem(Long memberId,Long itemId){
        Item result = itemRepository.findById(itemId).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이템이 없습니다.")
        );
        recentItemRepository.save(memberId,result);
        return result.toDto();
    }

    public List<ItemResponseDto> getRecentItems(Long memberId){
        return recentItemRepository.get(memberId).getList();
    }
}
