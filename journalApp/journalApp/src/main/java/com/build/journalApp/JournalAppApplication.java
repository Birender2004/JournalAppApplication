package com.build.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@Configuration
public class JournalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalAppApplication.class, args);
	}

//	@Bean
//	public PlatformTransactionManager trans(MongoDatabaseFactory dbFactory){
//		return new MongoTransactionManager(dbFactory);
//	}

	@Bean
	public RestTemplate redis(){
		return new RestTemplate();
	}

}

