package com.example.demo;

import com.example.demo.services.AuthService;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class AuthApplication{

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Autowired
	private AuthService authService;

	@Bean
	public static ModelMapper modelMapper(){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}

}
