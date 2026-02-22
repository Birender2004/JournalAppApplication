package com.build.journalApp.service;

import com.build.journalApp.entity.JournalEntry;
import com.build.journalApp.entity.User;
import com.build.journalApp.repository.journalRepository;
import com.build.journalApp.repository.userRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import org.slf4j.Logger;

@Component
@Slf4j
public class userService {

    @Autowired       // Injecting the dependency
    private userRepository interact;

    //private static final Logger logger= LoggerFactory.getLogger(userService.class);

    private static final PasswordEncoder pass= PasswordEncoderFactories.createDelegatingPasswordEncoder();


    public void create(User temp){
        interact.save(temp);
    }

    public void createS(User temp){
        try {
            temp.setPassword(pass.encode(temp.getPassword()));  // Encoding the password.
            //temp.setRoles(Arrays.asList("User"));
            interact.save(temp);
            log.error("You Baka");
        }

        catch (Exception e){

        }
    }

    public List<User> getAll(){
        return interact.findAll();
    }

    public Optional<User> findId(String id){
        return interact.findById(id);
    }

    public void remove(String id){
        interact.deleteById(id);
    }

    public void update(User temp){
        temp.setPassword(pass.encode(temp.getPassword()));
        temp.setRoles(new ArrayList<>());
        interact.save(temp);

    }

    public User findUser(String username){
        return interact.findByUsername(username);
    }

    public void saveAdmin(User user){
        interact.save(user);
    }

}