package com.build.journalApp.controller;


import com.build.journalApp.entity.JournalEntry;
import com.build.journalApp.entity.User;
import com.build.journalApp.service.journalEntryService;
import com.build.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class journalEntryController {

    @Autowired
    private journalEntryService service;

    @Autowired
    private userService UserService;

    @GetMapping("/fetchUserJournal")
    public ResponseEntity<List<JournalEntry>> get(){

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();
        User user=UserService.findUser(username);

        List<JournalEntry> data= user.getJoirnalEntry();

        if(data!=null && !data.isEmpty()){
            return new ResponseEntity(data,HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("fetchJournalById/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String id){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();

        User user= UserService.findUser(username);
        List<JournalEntry> collect= user.getJoirnalEntry().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry> local= service.findId(id);

            if(local.isPresent()) {
                return new ResponseEntity<>(local.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @PostMapping("/createEntry")
    public ResponseEntity<String> create(@RequestBody JournalEntry temp){
            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
            String username= auth.getName();
            service.create(temp,username);
            return new ResponseEntity<>("Resource created successfully",HttpStatus.CREATED);

        }



    @DeleteMapping("/deleteEntry/{id}")
    public ResponseEntity<JournalEntry> deleteById(@PathVariable String id){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();
        Optional<JournalEntry> local=service.findId(id);

        if(local.isPresent()) {
            service.remove(id,username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("update/{id}")
    public ResponseEntity<JournalEntry> putById(@PathVariable String id, @RequestBody JournalEntry temp){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();

        User user= UserService.findUser(username);
        List<JournalEntry> collect= user.getJoirnalEntry().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            JournalEntry local= service.findId(id).get();
                local.setTitle(temp.getTitle()!=null && !temp.getTitle().equals("") ? temp.getTitle(): local.getTitle());
                local.setContent(temp.getContent()!=null && !temp.getContent().equals("") ?temp.getContent() : local.getContent() );
                service.update(local);

                return new ResponseEntity<>(local,HttpStatus.OK);

        }

        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }





}
