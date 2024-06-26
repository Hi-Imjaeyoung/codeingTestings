package com.example.demo.Item.repository;

import com.example.demo.Item.dto.RecentItems;
import com.example.demo.Item.entity.Item;
import com.example.demo.global.ErrorCode;
import com.example.demo.global.MickException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RecentItemRepository {
    private final Map<Long, RecentItems> recentItemsMap = new ConcurrentHashMap<>();
    public RecentItems save(Long memberId, Item item){
        if(recentItemsMap.containsKey(memberId)){
            recentItemsMap.get(memberId).addItem(item);
        }else{
            recentItemsMap.put(memberId,new RecentItems(item));
        }
        return recentItemsMap.get(memberId);
    }
    public RecentItems get(Long memberId) throws MickException {
        try {
            return recentItemsMap.get(memberId);
        }catch (Exception e){
            log.error(e.toString());
            throw new MickException(ErrorCode.NOT_FIND_MEMBER);
        }
    }
}
