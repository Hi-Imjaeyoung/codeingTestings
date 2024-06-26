package com.example.demo.order.service;

import com.example.demo.global.ErrorCode;
import com.example.demo.global.MikException;
import com.example.demo.item.entity.Item;
import com.example.demo.item.repository.ItemRepository;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.order.entity.MyOrder;
import com.example.demo.order.entity.dto.OrderRequestDto;
import com.example.demo.order.entity.dto.OrderResponseDto;
import com.example.demo.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public OrderResponseDto createOrder(Long memberId, OrderRequestDto orderRequestDto){
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()-> new MikException(ErrorCode.NOT_FOUND_MEMBER, HttpStatus.BAD_REQUEST)
        );
        Item item = itemRepository.findById(orderRequestDto.getItemID()).orElseThrow(
                ()->new MikException(ErrorCode.NOT_FOUND_ITEM,HttpStatus.BAD_REQUEST)
        );
        if(item.getStock() >= orderRequestDto.getQuantity()){
            item.minusStock(orderRequestDto.getQuantity());
            MyOrder result = orderRepository.save(MyOrder.toEntity(member,item,orderRequestDto));
            return result.toDto();
        }else{
            throw new MikException(ErrorCode.NOT_ENOUGH_STOCK,HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
