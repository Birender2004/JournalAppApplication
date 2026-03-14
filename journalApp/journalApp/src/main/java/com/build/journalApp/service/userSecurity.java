package com.build.journalApp.service;

import com.build.journalApp.entity.User;
import com.build.journalApp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class userSecurity implements UserDetailsService {

    @Autowired
    private userRepository service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= service.findByUsername(username);

        if(user!=null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().
                    username(user.getUsername()).
                    password(user.getPassword()).
                    roles(user.getRoles().toArray(new String[0])).
                    build();

            return userDetails;
        }

        throw new UsernameNotFoundException("No such user found");

    }
    @Bean
    public PasswordEncoder encode(){
        return NoOpPasswordEncoder.getInstance();
    }
}
