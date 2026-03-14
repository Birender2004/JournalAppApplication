package com.build.journalApp.service;

import com.build.journalApp.entity.JournalEntry;
import com.build.journalApp.entity.User;
import com.build.journalApp.repository.journalRepository;
import com.build.journalApp.repository.userRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

// Here we will write the actual business logic i.e the set of rules that defines our system behaviour, here
// we write actual logic.

@Component
public class journalEntryService {
    @Autowired       // Injecting the dependency
    private journalRepository interact;

    @Autowired
    private userRepository UserRepository;

//    @Transactional
    public void create(JournalEntry temp, String username) {
        User user = UserRepository.findByUsername(username);

        if (user!=null) {

            JournalEntry local = interact.save(temp);

            if (user.getJoirnalEntry() == null) {
                user.setJoirnalEntry(new ArrayList<>());
            }

            user.getJoirnalEntry().add(local);
            UserRepository.save(user);

        }
    }
    public List<JournalEntry> getAll(){
        return interact.findAll();
    }

    public Optional<JournalEntry> findId(String id){
        return interact.findById(id);
    }

    public void remove(String id, String username){
        User user= UserRepository.findByUsername(username);
        boolean removed = user.getJoirnalEntry().removeIf(x -> x.getId().equals(id));

        if(removed) {
            UserRepository.save(user);
            interact.deleteById(id);
        }
    }

    public void update(JournalEntry temp){
        interact.save(temp);
    }

    public List<JournalEntry> findByUsername(String username){
        return null;
    }


}
