package com.bujo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bujo.services.AppUserModelDaoService;
@SpringBootApplication
public class BulletJournalApplication {	
	@Bean
	CommandLineRunner run(AppUserModelDaoService appUserModelDaoService) {
		return args -> {
			if (appUserModelDaoService.getAppUsers().isEmpty()) {
				appUserModelDaoService.setAppUsers();
			}			
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BulletJournalApplication.class, args);
	}

}
