package com.esprit.microservices.job.controller;

import com.esprit.microservices.job.entities.Job;
import com.esprit.microservices.job.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JobController {

    private final JobRepository jobRepository;

    @Autowired
    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @PostMapping("/add")
    public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    @GetMapping("/{idOrName}")
    public ResponseEntity<Job> getJobByIdOrName(@PathVariable String idOrName) {
        try {
            int jobId = Integer.parseInt(idOrName);
            Optional<Job> job = jobRepository.findById(jobId);
            return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (NumberFormatException e) {
            // If not a number, assume it's a name
            Optional<Job> job = jobRepository.findByService(idOrName);
            return job.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

    }

    @PatchMapping("/{id}/etat")
    public ResponseEntity<Job> updateJobEtat(@PathVariable Integer id, @RequestParam boolean etat) {
        return jobRepository.findById(id)
                .map(job -> {
                    job.setEtat(etat);
                    Job updatedJob = jobRepository.save(job);
                    return ResponseEntity.ok(updatedJob);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



}

