package com.build.journalApp.service;

import com.build.journalApp.repository.userRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class userServiceTests {
    @Autowired
    private userRepository repo;


//    @Test
    @ParameterizedTest
    @Disabled
    @ValueSource(strings = {
            "ram",
            "birender",
            "Birender"
    })
    public void testFindByUser(String name){
        assertNotNull(repo.findByUsername(name));

    }


    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,2,5",
            "4,4,1"
    })

    public void testSum(int a, int b, int res){
        assertEquals(res, a+b,"You baka failed for: "+a+" "+b);
    }
}
