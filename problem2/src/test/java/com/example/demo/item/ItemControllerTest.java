package com.example.demo.item;

import com.example.demo.Item.controller.ItemController;
import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.service.ItemService;
import com.example.demo.global.ErrorCode;
import com.example.demo.global.GlobalExceptionHandler;
import com.example.demo.global.MikException;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    @Mock
    private ItemService itemService;
    @InjectMocks
    private ItemController itemController;
    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
        gson =new Gson();
    }

    @Test
    @DisplayName("아이템 등록 실패 : 헤더 누락")
    void failItemCreateHeaderIsNull() throws Exception {
        //given
        final String url = "/api/item/create";
        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(new ItemRequestDto("item",1000)))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        resultActions.andExpect(status().is4xxClientError());
    }
    @ParameterizedTest
    @MethodSource("parameters")
    @DisplayName("아이템 등록 실패 : 바디 데이터 누락")
    void failItemCreateBodyDataNull(String name,Integer price) throws Exception {
        //given
        final String url = "/api/item/create";
        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header("memberId",1L)
                        .content(gson.toJson(new ItemRequestDto(name,price)))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        resultActions.andExpect(status().is4xxClientError());
    }
    public static Stream<Arguments> parameters(){
        return Stream.of(
                Arguments.of("test",null),
                Arguments.of(null,1000));
    }

    @Test
    @DisplayName("아이템 등록 실패 : 서비스레이어 비지니스 에러 발생")
    void failItemCreateExceptionInServiceLayer() throws Exception {
        //given
        final String url = "/api/item/create";
        doThrow(new MikException(ErrorCode.NOT_FIND_MEMBER)).when(itemService).itemCreate(any(Long.class),any(ItemRequestDto.class));
        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header("memberId",1L)
                        .content(gson.toJson(new ItemRequestDto("item1",1000)))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("아이템 등록 성공")
    void successItemCreate()throws Exception{
        //given
        final String url = "/api/item/create";
        doReturn(itemResponseDto()).when(itemService).itemCreate(any(Long.class),any(ItemRequestDto.class));
        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .header("memberId",1L)
                        .content(gson.toJson(new ItemRequestDto("item1",1000)))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        resultActions.andExpectAll(
                status().isCreated(),
                jsonPath("$.id").value(1L),
                jsonPath("$.name").value("item1"),
                jsonPath("$.price").value(1000)
        );
    }
    public ItemResponseDto itemResponseDto(){
        return new ItemResponseDto(1L,"item1",1000);
    }
}
