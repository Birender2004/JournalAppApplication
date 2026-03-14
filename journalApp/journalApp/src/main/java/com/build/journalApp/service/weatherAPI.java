package com.build.journalApp.service;

import com.build.journalApp.cache.appCache;
import com.build.journalApp.entity.weatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class weatherAPI {
    @Value("${weather_api}")
    private String api;

    @Autowired
    private appCache cache;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private redisService service;

    public weatherResponse get(String city){
        weatherResponse Response = service.get(city, weatherResponse.class);

        if(Response!=null){
            return Response;
        }

        String link= cache.map.get("weather_api").replace("<api>",api).replace("<city>",city);
        ResponseEntity<weatherResponse> exchange = restTemplate.exchange(link, HttpMethod.GET, null, weatherResponse.class);
        weatherResponse body = exchange.getBody();
        if(body!=null) {
            service.set(city, body, 300l);
        }
        return body;
    }

}
