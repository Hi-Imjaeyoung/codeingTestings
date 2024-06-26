package com.example.demo.Item.contoller;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("api/item/create")
    public ResponseEntity<?> itemCreate(@RequestHeader Long memberId, @RequestBody ItemRequestDto itemRequestDto){
        ItemResponseDto itemResponseDto = itemService.createItem(itemRequestDto);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("api/item/detail/{item_id}")
    public ResponseEntity<?> showItemDetail(@PathVariable("item_id") Long itemId,@RequestHeader Long memberId){
        ItemResponseDto itemResponseDto = itemService.showItem(memberId,itemId);
        return new ResponseEntity<>(itemResponseDto,HttpStatus.OK);
    }
    @GetMapping("api/item/recent")
    public ResponseEntity<?> recentItem(@RequestHeader Long memberId){
        List<ItemResponseDto> itemResponseDtoList = itemService.getRecentItems(memberId);
        return new ResponseEntity<>(itemResponseDtoList,HttpStatus.OK);
    }
}
