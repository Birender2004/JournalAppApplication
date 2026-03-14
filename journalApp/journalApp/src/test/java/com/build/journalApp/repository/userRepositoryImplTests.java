package com.build.journalApp.repository;

import com.build.journalApp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class userRepositoryImplTests {

    @Autowired
    private userRepositoryImpl repo;

    @Test
    public void get(){
        List<User> users= repo.getUserForSA();

    }
}
