package com.esprit.microservices.candidate;

import com.esprit.microservices.candidate.Repositories.CandidateRepository;
import com.esprit.microservices.candidate.entities.candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CandidateApplication {
    public static void main(String[] args) {
        SpringApplication.run(CandidateApplication.class, args);
    }
    @Autowired
    private CandidateRepository repository;
    @Bean
    ApplicationRunner init() {
        return (args) -> {
// save
            repository.save(new candidate("Mariem", "Ch", "ma@esprit.tn"));
            repository.save(new candidate("Sarra", "ab", "sa@esprit.tn"));
            repository.save(new candidate("Mohamed", "ba", "mo@esprit.tn"));
// fetch
            repository.findAll().forEach(System.out::println);
        };
    }
}
