package com.example.demo.Item.entity;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Builder
public class Item {
    @Id
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    private UUID id;
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
        return new ItemResponseDto(this.id.toString(),this.name,this.price);
    }
}
