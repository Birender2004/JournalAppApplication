package com.build.journalApp.controller;


import com.build.journalApp.entity.User;
import com.build.journalApp.entity.weatherResponse;
import com.build.journalApp.repository.userRepository;
import com.build.journalApp.service.userService;
import com.build.journalApp.service.weatherAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class userEntryController {

    @Autowired
    private userService service;

    @Autowired
    public userRepository userRepo;

    @Autowired
    private weatherAPI api;


    @PutMapping("/update")
    public String updateUser(@RequestBody User newUser){
       Authentication auth= SecurityContextHolder.getContext().getAuthentication();
       String name= auth.getName();
       User oldUser= service.findUser(name);

       oldUser.setUsername(newUser.getUsername());
       oldUser.setPassword(newUser.getPassword());

       service.update(oldUser);
       return "Data updated";
    }

    @DeleteMapping("/delete")
    public String deleteUser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name= auth.getName();
        User oldUser= service.findUser(name);

        userRepo.deleteByUsername(name);
        return "Entry deleted successfully";
    }

    @GetMapping
    public ResponseEntity<String> greetings(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name= auth.getName();

        weatherResponse obj = api.get("kashmir");
        if (obj.getSuccess() != null && !obj.getSuccess()) {
            return new ResponseEntity<>(obj.getError().getInfo(),HttpStatus.NOT_FOUND);
        }

        else {
            int temperature= obj.getCurrent().getTemperature();
            return new ResponseEntity<>("Hello "+ name+" todays temperature is: "+temperature,HttpStatus.OK);
        }

    }
}
