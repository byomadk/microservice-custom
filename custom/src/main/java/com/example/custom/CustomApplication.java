package com.example.custom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.custom.repository")
@EntityScan("com.example.custom.entity")
@ComponentScan("com.example.custom")
@SpringBootApplication
public class CustomApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomApplication.class, args);
	}

}
