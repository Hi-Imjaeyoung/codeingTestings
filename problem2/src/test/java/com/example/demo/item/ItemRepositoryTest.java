package com.example.demo.item;

import com.example.demo.Item.entity.Item;
import com.example.demo.Item.repository.ItemRepository;
import com.example.demo.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application.yml")
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Test
    @DisplayName("아이템 저장")
    void successSaveItem(){
        //given
        final Item item = Item.builder()
                .name("item1")
                .price(1000)
                .member(member())
                .build();
        //when
        final Item result = itemRepository.save(item);
        //then
        assertThat(result.getName()).isEqualTo("item1");
        assertThat(result.getPrice()).isEqualTo(1000);
        assertThat(result.getMember().getId()).isEqualTo(1L);
    }
    public Member member(){
        return Member.builder()
                .id(1L)
                .name("tester")
                .email("test@com")
                .build();
    }
}
