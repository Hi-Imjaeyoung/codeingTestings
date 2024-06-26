package com.example.demo.Item.entity;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
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
