package com.build.journalApp.cache;


import com.build.journalApp.entity.config_journal;
import com.build.journalApp.repository.confi_database;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class appCache {

    public Map<String, String> map= new HashMap<>();

    @Autowired
    private confi_database repo;

    @PostConstruct
    public void initialize(){
        List<config_journal> all = repo.findAll();

        for(config_journal i: all){
            map.put(i.getKey(),i.getValue());
        }
    }

}
