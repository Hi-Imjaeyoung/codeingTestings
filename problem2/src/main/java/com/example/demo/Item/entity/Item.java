package com.example.demo.Item.entity;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    public static Item toEntity(ItemRequestDto itemRequestDto,Member member){
        return Item.builder()
                .name(itemRequestDto.getName())
                .member(member)
                .price(itemRequestDto.getPrice())
                .build();
    }
    public ItemResponseDto toDto(){
        return new ItemResponseDto(this.id,this.name,this.price);
    }
}
