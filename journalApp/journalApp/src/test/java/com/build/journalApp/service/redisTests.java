package com.build.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class redisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        redisTemplate.opsForValue().set("email","singh@2004");
        redisTemplate.opsForValue().set("salary","25L");
        Object name = redisTemplate.opsForValue().get("name");
        Object email = redisTemplate.opsForValue().get("email");


    }
}
