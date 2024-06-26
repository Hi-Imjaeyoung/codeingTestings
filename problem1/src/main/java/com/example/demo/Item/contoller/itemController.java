package com.example.demo.Item.contoller;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class itemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("api/item/create")
    public ResponseEntity<?> itemCreate(@RequestBody ItemRequestDto itemRequestDto){
        ItemResponseDto itemResponseDto = itemService.createItem(itemRequestDto);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.CREATED);
    }
}
