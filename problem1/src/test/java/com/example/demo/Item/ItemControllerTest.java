package com.example.demo.Item;

import com.example.demo.Item.contoller.ItemController;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.service.ItemService;
import com.example.demo.global.ErrorCode;
import com.example.demo.global.GlobalExceptionHandler;
import com.example.demo.global.MickException;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    @InjectMocks
    private ItemController itemController;
    @Mock
    private ItemService itemService;
    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
        gson = new Gson();
    }
    @Test
    @DisplayName("아이템 단건 조회 실패 : 헤더 누락")
    void failShowItemDetailHeaderIsNull() throws Exception {
        //when
        final String url  = "/api/item/detail/{item_id}";
        //given
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url,UUID.randomUUID().toString())
        );
        //then
        resultActions.andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("아이템 단건 조회 성공")
    void successShowItemDetailApi() throws Exception {
        //when
        final String url  = "/api/item/detail/{item_id}";
        final Long itemId = 1L;
        final Long memberId = 1L;
        doReturn(itemResponseDto()).when(itemService).showItem(memberId,itemId);
        //given
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url,itemId)
                        .header("memberId",memberId)
        );
        //then
        resultActions.andExpectAll(
                status().isOk(),
                jsonPath("$.name").value("item1"),
                jsonPath("$.price").value(1000)
        );
        //verify
        verify(itemService,times(1)).showItem(memberId,itemId);
    }
    @Test
    @DisplayName("최근 본 상품 목록 사용자 헤더 누락")
    void headerDataIsNull() throws Exception {
        //when
        final String url = "/api/item/recent";
        //given
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON)

        );
        //then
        resultActions.andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("최근 본 상품 서비스 도메인 에러 발생")
    void serviceLayerException() throws Exception{
        //when
        final String url = "/api/item/recent";
        doThrow(new MickException(ErrorCode.NOT_FIND_MEMBER)).when(itemService).getRecentItems(any(Long.class));
        //given
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("memberId", 1L)
        );
        //then
        resultActions.andExpect(status().isBadRequest());
        //verify
        verify(itemService,times(1)).getRecentItems(any(Long.class));
    }
    @Test
    @DisplayName("최근 본 상품 API 정상 작동")
    void successRecentItemsAPI() throws Exception {
        //when
        final String url = "/api/item/recent";
        doReturn(itemResponseDtoList()).when(itemService).getRecentItems(any(Long.class));
        //given
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .header("memberId",1L)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(print()).andReturn();
        resultActions.andExpectAll(
                status().isOk(),
                jsonPath("$[0].name").value("item1"),
                jsonPath("$[1].name").value("item2")
        );
    }

    public List<ItemResponseDto> itemResponseDtoList(){
        return List.of(new ItemResponseDto(1L,"item1",1000)
                ,new ItemResponseDto(2L,"item2",2000));
    }
    public ItemResponseDto itemResponseDto(){
        return new ItemResponseDto(1L,"item1",1000);
    }

}
