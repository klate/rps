package com.github.klate.rps.config;

import com.github.klate.rps.entity.GameResult;
import com.github.klate.rps.repository.GameResultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(GameResultRepository repo){
        return args -> {
            repo.save(new GameResult('c', "testkai", 's', 'r'));
        };
    }

}
