package com.github.klate.rps;

import com.github.klate.rps.controller.GameController;
import com.github.klate.rps.controller.StatusController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RockPaperScissorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(
			new Class[] {
				GameController.class,
				StatusController.class
			}
			, args);
	}

}
