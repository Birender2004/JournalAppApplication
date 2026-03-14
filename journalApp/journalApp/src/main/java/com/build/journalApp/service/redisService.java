package com.build.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class redisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entity){
        Object obj= redisTemplate.opsForValue().get(key);
        ObjectMapper mapper = new ObjectMapper();
        if(obj==null) return null;

        return mapper.readValue(obj.toString(), entity);
    }

    public void set(String key, Object o, Long ttl){
        ObjectMapper mapper= new ObjectMapper();
        String str= mapper.writeValueAsString(o);
        redisTemplate.opsForValue().set(key,str,ttl, TimeUnit.SECONDS);
    }
}
