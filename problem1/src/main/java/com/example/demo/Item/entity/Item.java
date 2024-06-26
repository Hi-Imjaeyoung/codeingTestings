package com.example.demo.Item.entity;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private int price;

    public static Item toEntity(ItemRequestDto itemRequestDto){
        return Item.builder()
                .name(itemRequestDto.getName())
                .price(itemRequestDto.getPrice())
                .build();
    }
    public ItemResponseDto toDto(){
        return new ItemResponseDto(this.id,this.name,this.price);
    }
}
