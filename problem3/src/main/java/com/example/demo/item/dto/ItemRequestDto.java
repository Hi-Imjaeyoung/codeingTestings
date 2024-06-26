package com.example.demo.item.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {
    private String name;
    private String category;
    private int price;
    private int stock;
}
