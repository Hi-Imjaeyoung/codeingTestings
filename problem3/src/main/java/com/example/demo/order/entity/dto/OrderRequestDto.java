package com.example.demo.order.entity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private Long itemID;
    private int quantity;
}
