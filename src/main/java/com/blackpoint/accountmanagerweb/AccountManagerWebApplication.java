package com.blackpoint.accountmanagerweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AccountManagerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagerWebApplication.class, args);
	}

}
