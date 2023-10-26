package com.example.demo;

import com.example.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication{

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Autowired
	private AuthService authService;

}
