package com.example.demo;

import com.example.demo.Item.contoller.ItemController;
import com.example.demo.Item.dto.ItemRequestDto;
import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.repository.ItemRepository;
import com.example.demo.Item.repository.RecentItemRepository;
import com.example.demo.Item.service.ItemService;
import com.example.demo.member.repository.MemberRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
class Problem1ApplicationTests {
	@Autowired
	private ItemController itemController;
	@Autowired
	private ItemService itemService;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private RecentItemRepository recentItemRepository;
	private MockMvc mockMvc;
	private Gson gson;

	@Test
	@DisplayName("최근 본 상품 조회 통합테스트")
	void recentItemsApi() throws Exception {
		//given
		mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
		gson = new Gson();
		Long memberId = 1l;
		//when
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/api/item/create")
						.header("memberId",memberId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(new ItemRequestDto("item1",1000)))
		);
		ItemResponseDto itemResponseDto=gson.fromJson(resultActions.andReturn().getResponse().getContentAsString(),ItemResponseDto.class);
		log.info("item1Id :" +itemResponseDto.getId());
		Long item1Id = itemResponseDto.getId();
		resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/api/item/create")
						.header("memberId",memberId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(new ItemRequestDto("item2",2000)))
		);
		ItemResponseDto itemResponseDto2 =gson.fromJson(resultActions.andReturn().getResponse().getContentAsString(),ItemResponseDto.class);
		log.info("item2Id:" + itemResponseDto2.getId() +" "+itemResponseDto2.getName());
		Long item2Id = itemResponseDto2.getId();
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/item/detail/{item_id}",item1Id)
						.header("memberId",memberId)
		);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/item/detail/{item_id}",item2Id)
						.header("memberId",memberId)
		);
		resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/item/recent")
						.header("memberId",memberId)
		);
		resultActions.andDo(print());
		resultActions.andExpectAll(
				status().isOk(),
				jsonPath("$[0].name").value("item2"),
				jsonPath("$[1].name").value("item1")
		);
	}

}
