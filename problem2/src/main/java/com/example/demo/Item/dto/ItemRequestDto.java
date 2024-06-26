package com.example.demo.Item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class ItemRequestDto {
    @NotNull
    private String name;
    @NotNull
    private Integer price;
}
