package com.example.demo.item.controller;

import com.example.demo.item.dto.ItemRequestDto;
import com.example.demo.item.dto.ItemResponseDto;
import com.example.demo.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("api/item/create")
    public ResponseEntity<?> itemCreate(@RequestHeader Long memberID, @RequestBody ItemRequestDto itemRequestDto){
        ItemResponseDto itemResponseDto = itemService.createItem(memberID,itemRequestDto);
        return new ResponseEntity<>(itemRequestDto, HttpStatus.CREATED);
    }
}
