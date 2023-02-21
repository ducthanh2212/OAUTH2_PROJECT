package com.example.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class FeedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedServiceApplication.class, args);
	}

	
//	@Bean
//	NewTopic notification() {
//		//topic name, partition number, replication number (normal replication number = broker number)
//		return new NewTopic("notification", 2, (short) 1);
//	}
//	
//	@Bean
//	NewTopic statistic() {
//		//topic name, partition number, replication number (normal replication number = broker number)
//		return new NewTopic("statistic", 1, (short)1);
//	}
}
