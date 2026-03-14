package com.build.journalApp.service;

import com.build.journalApp.entity.mailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class emailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(mailEntity data){

        try{
            SimpleMailMessage mail= new SimpleMailMessage();
            mail.setTo(data.getTo());
            mail.setSubject(data.getSubject());
            mail.setText(data.getBody());

            javaMailSender.send(mail);
        }

        catch(Exception e){
            System.out.println("Error............................");
        }
    }
}
