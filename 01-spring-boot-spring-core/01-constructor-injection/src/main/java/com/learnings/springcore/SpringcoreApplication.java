package com.learnings.springcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {"com.learnings.additionalPackages",
		"com.learnings.springcore"}
)
public class SpringcoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoreApplication.class, args);
	}

}
