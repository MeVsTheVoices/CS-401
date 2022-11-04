package com.cs401.hw4.hw4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Hw4Application {

	public static void main(String[] args) {
		SpringApplication.run(Hw4Application.class, args);
	}

}
