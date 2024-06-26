package com.example.demo.item.entity;

import com.example.demo.item.dto.ItemRequestDto;
import com.example.demo.item.dto.ItemResponseDto;
import com.example.demo.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private int price;
    @Column
    private int stock;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member seller;

    public static Item toEntity(Member member, ItemRequestDto itemRequestDto){
        return Item.builder()
                .name(itemRequestDto.getName())
                .category(itemRequestDto.getCategory())
                .price(itemRequestDto.getPrice())
                .stock(itemRequestDto.getStock())
                .seller(member)
                .build();
    }
    public ItemResponseDto toDto(){
        return ItemResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .memberID(this.seller.getId())
                .price(this.price)
                .build();
    }
    public void minusStock(int quantity){
        this.stock -= quantity;
    }
}
