package com.chatapp.chatapp;

import com.chatapp.chatapp.Utils.LoggerUtils.CaLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatappApplication {

	public static void main(String[] args) {
		CaLogger.redirectSystemOutToLogger();
		SpringApplication.run(ChatappApplication.class, args);
	}

}
