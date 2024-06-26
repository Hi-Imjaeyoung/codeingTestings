package com.example.demo.Item;

import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.dto.RecentItems;
import com.example.demo.Item.entity.Item;
import com.example.demo.Item.repository.RecentItemRepository;
import com.example.demo.Item.service.ItemService;
import com.example.demo.global.ErrorCode;
import com.example.demo.global.MickException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;
    @Mock
    private RecentItemRepository recentItemRepository;

    @Test
    @DisplayName("최근 아이템 리스트를 가진 회원의 키를 찾지 못하는 경우")
    void notFindMemberIdInRecentItemsData(){
        //given
        doThrow(new MickException(ErrorCode.NOT_FIND_MEMBER)).when(recentItemRepository).get(any(Long.class));
        //whens
        MickException result = assertThrows(MickException.class
                ,()->itemService.getRecentItems(1L)
                );
        //then
        assertThat(result.getErrorCode().getCode()).isEqualTo("E1");
        assertThat(result.getErrorCode().getMessage()).isEqualTo("해당 멤버를 찾을 수 없습니다.");
        assertThat(result.getErrorCode().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("최근 아이템 목록 조회 정상 작동")
    void findRecentItems(){
        //given
        doReturn(recentItems()).when(recentItemRepository).get(any(Long.class));
        //when
        final List<ItemResponseDto> result = itemService.getRecentItems(1L);
        //then
        assertThat(result.get(0).getName()).isEqualTo("item1");
        //verify
        verify(recentItemRepository,times(1)).get(any(Long.class));
    }
    public RecentItems recentItems(){
        return new RecentItems(Item.builder()
                .id(1L)
                .name("item1")
                .price(1000)
                .build()
        );
    }
}
