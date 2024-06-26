package com.example.demo.Item;

import com.example.demo.Item.dto.ItemResponseDto;
import com.example.demo.Item.dto.RecentItems;
import com.example.demo.Item.dto.SelectItem;
import com.example.demo.Item.entity.Item;
import com.example.demo.Item.repository.RecentItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class RecentItemRepoTest {
    @Autowired
    private RecentItemRepository recentItemRepository;

    @Test
    @DisplayName("최근 본 아이템 저장")
    void saveRecentItem(){
        //given
        final Long memberId = 1L;
        //when
        final RecentItems recentItems = recentItemRepository.save(memberId,item("test"));
        //then
        assertThat(recentItems.getList().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("최근 본 아이템 조회")
    void selectRecentItem(){
        //given
        final Long memberId = 1L;
        recentItemRepository.save(memberId,item("test"));
        //when
        final RecentItems recentItems = recentItemRepository.get(memberId);
        //then
        assertThat(recentItems.getList().size()).isEqualTo(1);
        assertThat(recentItems.getList().get(0).getName()).isEqualTo("test");
        assertThat(recentItems.getList().get(0).getPrice()).isEqualTo(1000);
    }
    @Test
    @DisplayName("이미 존재하는 최근아이템 목록에 다른 아이템 저장")
    void addRecentItemsExistKey(){
        //given
        final Long memberId = 1L;
        recentItemRepository.save(memberId,item("test"));
        recentItemRepository.save(memberId,item("test2"));
        //when
        final RecentItems recentItems = recentItemRepository.get(1L);
        //then
        assertThat(recentItems.getList().size()).isEqualTo(2);
        assertThat(recentItems.getList().get(1).getName()).isEqualTo("test");
        assertThat(recentItems.getList().get(0).getName()).isEqualTo("test2");
    }
    @Test
    @DisplayName("이미 존재하는 최근아이템 목록에 같은 아이템 저장")
    void addRecentItemsExistItem(){
        //given
        final Long memberId = 1L;
        final Item item1 = item("item1");
        recentItemRepository.save(memberId,item1);
        recentItemRepository.save(memberId,item("item2"));
        recentItemRepository.save(memberId,copyItem(item1));
        //when
        final RecentItems recentItems = recentItemRepository.get(memberId);
        //then
        for(ItemResponseDto itemResponseDto : recentItems.getList()){
            log.info(String.valueOf(itemResponseDto.hashCode()));
        }
        assertThat(recentItems.getList().size()).isEqualTo(2);
        assertThat(recentItems.getList().get(1).getName()).isEqualTo("item2");
        assertThat(recentItems.getList().get(0).getName()).isEqualTo("item1");
    }
    public Item item(String name){
        return Item.builder()
                .id(1L)
                .name(name)
                .price(1000)
                .build();
    }
    public Item copyItem(Item item){
        return Item.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }

}
