package com.si.inter;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterApplication.class, args);
	}

	@PostConstruct
	public void start() {

	}
}
