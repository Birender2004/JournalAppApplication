package com.build.journalApp.service;

import com.build.journalApp.entity.User;
import com.build.journalApp.repository.userRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

//@SpringBootTest
public class userSecurityTest {

//    @Autowired
    @InjectMocks
    private userSecurity obj;

//    @MockitoBean
    @Mock
    private userRepository service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserTest(){
        when(service.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("shh").password("123").roles(new ArrayList<>()).build());
        UserDetails user= obj.loadUserByUsername("ram");
        assertNotNull(user);


    }
}
