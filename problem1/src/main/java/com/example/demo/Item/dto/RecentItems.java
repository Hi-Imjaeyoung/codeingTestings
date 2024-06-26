package com.example.demo.Item.dto;

import com.example.demo.Item.entity.Item;

import java.util.*;
import java.util.stream.Collectors;

public class RecentItems {
    private final Set<SelectItem> itemSet;

    public RecentItems(Item item) {
        itemSet = new LinkedHashSet<>();
        addItem(item);
    }
    public void addItem(Item item){
        SelectItem selectItem = new SelectItem(item);
        if(itemSet.contains(selectItem)){
            itemSet.remove(selectItem);
        }
        itemSet.add(new SelectItem(item));
    }

    public void clearList(){
        itemSet.clear();
    }

    public List<ItemResponseDto> getList(){
        List<ItemResponseDto> itemResponseDtos = itemSet.stream()
                .map(SelectItem::toDto)
                .collect(Collectors.toList());
        Collections.reverse(itemResponseDtos);
        return itemResponseDtos;
    }
}
