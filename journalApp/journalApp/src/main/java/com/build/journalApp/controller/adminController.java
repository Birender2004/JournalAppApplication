package com.build.journalApp.controller;


import com.build.journalApp.entity.User;
import com.build.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private userService service;

    private static final PasswordEncoder pass= PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @GetMapping("/all-user")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> all = service.getAll();

        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createAdmin(@RequestBody User user){
        user.setPassword(pass.encode(user.getPassword()));
        service.saveAdmin(user);
    }

}
