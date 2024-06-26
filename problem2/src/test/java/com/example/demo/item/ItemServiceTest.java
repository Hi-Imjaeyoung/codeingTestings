package com.example.demo.item;

import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.entity.Item;
import com.example.demo.Item.repository.ItemRepository;
import com.example.demo.Item.service.ItemService;
import com.example.demo.global.ErrorCode;
import com.example.demo.global.MikException;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private ItemService itemService;

    @Test
    @DisplayName("아이템 생성 실패 : 멤버 조회 실패")
    void failItemCreateNotFoundMember(){
        //given
        doThrow(new MikException(ErrorCode.NOT_FIND_MEMBER)).when(memberRepository).findById(any(Long.class));
        //when
        MikException mikException = assertThrows(MikException.class,()->
                itemService.itemCreate(1L,new ItemRequestDto("item1",1000)));
        //then
        assertThat(mikException.getErrorCode()).isEqualTo(ErrorCode.NOT_FIND_MEMBER);
    }

    @Test
    @DisplayName("아이템 생성 성공")
    void successCreateItem(){
        //given
        doReturn(member()).when(memberRepository).findById(any(Long.class));
        doReturn(item()).when(itemRepository).save(any(Item.class));
        //when
        ItemResponseDto result = itemService.itemCreate(1L,new ItemRequestDto("item1",1000));
        //then
        assertThat(result.getName()).isEqualTo("item1");
        assertThat(result.getPrice()).isEqualTo(1000);
        //verify
        verify(memberRepository,times(1)).findById(1L);
        verify(itemRepository,times(1)).save(any(Item.class));
    }
    public Optional<Member> member(){
        return Optional.of(Member.builder()
                .name("test")
                .email("test@com")
                .id(1L)
                .build());
    }
    public Item item(){
        return Item.builder()
                .name("item1")
                .member(member().get())
                .price(1000)
                .build();
    }
}
