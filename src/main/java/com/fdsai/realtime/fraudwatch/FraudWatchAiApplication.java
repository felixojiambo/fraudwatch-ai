package com.fdsai.realtime.fraudwatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FraudWatchAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudWatchAiApplication.class, args);
	}

}
