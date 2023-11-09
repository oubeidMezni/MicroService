package com.esprit.microservices.candidate.Repositories;

import com.esprit.microservices.candidate.entities.candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<candidate, Integer> {
    @Query("select c from candidate c where c.nom like :name")
    public Page<candidate> candidatByNom(@Param("name") String n, Pageable pageable);
}
