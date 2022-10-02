package com.github.klate.rps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.github.klate.rps.entity")
@EnableJpaRepositories(basePackages = "com.github.klate.rps.repository")
@ComponentScan(basePackages = {"com.github.klate.rps.controller", "com.github.klate.rps.service", "com.github.klate.rps.exception"})
public class RockPaperScissorsApplication{

	public static void main(String[] args) {
		SpringApplication.run(
			new Class[] {
				RockPaperScissorsApplication.class
			}
			, args);
	}

}
