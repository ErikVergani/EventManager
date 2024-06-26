package com.univates.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
	}

	@Bean
    PasswordEncoder getPasswordEncoder()
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        return encoder;
    }
	
	@Bean
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
