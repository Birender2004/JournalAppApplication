package com.build.journalApp.scheduler;

import com.build.journalApp.cache.appCache;
import com.build.journalApp.entity.JournalEntry;
import com.build.journalApp.entity.User;
import com.build.journalApp.entity.mailEntity;
import com.build.journalApp.enums.sentiment;
import com.build.journalApp.repository.userRepositoryImpl;
import com.build.journalApp.service.emailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class userScheduler {

    @Autowired
    private appCache cache;

    @Autowired
    private userRepositoryImpl repo;

    @Autowired
    private emailService service;

    private mailEntity data= new mailEntity();

    public void fetchUserAndSendMail(){
        List<User> users= repo.getUserForSA();

        for(User user:users){
            List<JournalEntry> entry= user.getJoirnalEntry();
            List<sentiment> emotion= entry.stream().map(x->x.getMood()).collect(Collectors.toList());
            Map<sentiment, Integer> map= new HashMap<>();

            for(sentiment s: emotion){
                if(s!=null){
                    map.put(s,map.getOrDefault(s,0)+1);
                }
            }

            sentiment mostCount= null;
            int maxCount=0;

            for(Map.Entry<sentiment,Integer> m: map.entrySet()){
                if(m.getValue()>maxCount){
                    maxCount= m.getValue();
                    mostCount= m.getKey();
                }

            }
            if(mostCount!=null){
                data.setTo(user.getEmail());
                data.setSubject("Sentiment analysis result");
                data.setBody(mostCount.toString());
                service.sendMail(data);
            }
        }


        }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        cache.initialize();
    }
}
