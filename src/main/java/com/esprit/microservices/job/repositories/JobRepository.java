package com.esprit.microservices.job.repositories;

import com.esprit.microservices.job.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {

    Optional<Job> findByService(String idOrName);
}

