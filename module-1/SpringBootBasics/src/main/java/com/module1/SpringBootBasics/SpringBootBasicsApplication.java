package com.module1.SpringBootBasics;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication

public class SpringBootBasicsApplication implements CommandLineRunner {
	  @Autowired
      Map<String,NotificationService> notificationService = new HashMap<>();
//	 public SpringBootBasicsApplication(NotificationService notificationService)
//	 {
//		 this.notificationService=notificationService;
//	 }
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBasicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(var notificationService: notificationService.entrySet())
		{
			System.out.println(notificationService.getKey());
			notificationService.getValue().notify("hello");
		}
	}
}
