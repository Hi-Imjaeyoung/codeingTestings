package com.example.demo.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<Long,Integer> redisTemplate;

    @Transactional(readOnly = true)
    public int getValuesItemCount(Long key) {
        ValueOperations values = redisTemplate.opsForValue();
        return Integer.parseInt((String) values.get(key));
    }

    public void setItemQuantity(Long id, int count) {
        ValueOperations values = redisTemplate.opsForValue();
        values.set(id,String.valueOf(count));
    }
}