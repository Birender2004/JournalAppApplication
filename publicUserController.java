package com.build.journalApp.controller;


import com.build.journalApp.entity.User;
import com.build.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public")
public class publicUserController {

    @Autowired
    private userService service;

    @GetMapping("/fetchAll")
    public List<User> getAllUsers(){
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public User fetchById(@PathVariable String id){
        Optional<User> user= service.findId(id);

        if(user.isPresent()) return user.get();
        return null;
    }

    @PostMapping
    public void createUser(@RequestBody User temp){
        service.createS(temp);
    }
}
