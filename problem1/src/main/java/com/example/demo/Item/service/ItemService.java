package com.example.demo.Item.service;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.entity.Item;
import com.example.demo.Item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public ItemResponseDto createItem(ItemRequestDto itemRequestDto){
        Item item = Item.toEntity(itemRequestDto);
        Item result = itemRepository.save(item);
        return result.toDto();
    }
}
