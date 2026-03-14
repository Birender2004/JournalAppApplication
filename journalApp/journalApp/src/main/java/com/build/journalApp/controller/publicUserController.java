package com.build.journalApp.controller;


import com.build.journalApp.entity.User;
import com.build.journalApp.entity.mailEntity;
import com.build.journalApp.service.emailService;
import com.build.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public")
public class publicUserController {

    @Autowired
    private userService service;

    @Autowired
    private emailService email;

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

    @PostMapping("/sendMail")
    public ResponseEntity<String> send(@RequestBody mailEntity data){
        email.sendMail(data);

        return new ResponseEntity<>("Email sent successfully to: "+data.getTo(), HttpStatus.OK);
    }
}
