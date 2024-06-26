package com.example.demo.order.controller;

import com.example.demo.order.entity.dto.OrderRequestDto;
import com.example.demo.order.entity.dto.OrderResponseDto;
import com.example.demo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("api/order/create")
    public ResponseEntity<?> createOrder(@RequestHeader Long memberId,
                                         @RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto orderResponseDto = orderService.createOrder(memberId,orderRequestDto);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.CREATED);
    }
}
