package com.example.demo.order.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponseDto {
    private String memberName;
    private String itemName;
    private int totalPrice;
}
