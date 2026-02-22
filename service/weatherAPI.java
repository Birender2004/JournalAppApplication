package com.build.journalApp.service;

import com.build.journalApp.cache.appCache;
import com.build.journalApp.entity.weatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public weatherResponse get(String city){
        String link= cache.map.get("weather_api").replace("<api>",api).replace("<city>",city);
        ResponseEntity<weatherResponse> exchange = restTemplate.exchange(link, HttpMethod.GET, null, weatherResponse.class);
        weatherResponse body = exchange.getBody();
        return body;
    }

}
