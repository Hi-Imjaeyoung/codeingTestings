package com.example.demo.Item.controller;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping("api/item/create")
    public ResponseEntity<?> itemCreate(@RequestHeader("memberId")Long memberId,
                                        @RequestBody@Valid ItemRequestDto itemRequestDto){
        ItemResponseDto itemResponseDto = itemService.itemCreate(memberId,itemRequestDto);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.CREATED);
    }
}
