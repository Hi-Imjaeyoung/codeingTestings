package com.example.demo.Item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseDto {
    private Long id;
    private String name;
    private int price;
    public ItemResponseDto(Long id, String name,int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
