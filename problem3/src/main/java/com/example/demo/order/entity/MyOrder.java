package com.example.demo.order.entity;

import com.example.demo.item.entity.Item;
import com.example.demo.member.entity.Member;
import com.example.demo.order.entity.dto.OrderRequestDto;
import com.example.demo.order.entity.dto.OrderResponseDto;
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
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
    private int quantity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;
    public static MyOrder toEntity(Member member, Item item, OrderRequestDto orderRequestDto){
        return MyOrder.builder()
                .item(item)
                .member(member)
                .quantity(orderRequestDto.getQuantity())
                .build();
    }
    public OrderResponseDto toDto(){
        return new OrderResponseDto(this.member.getName(),this.item.getName(),this.item.getPrice() * this.quantity);
    }
}
