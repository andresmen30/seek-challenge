package com.seek.seekchallenge.infraestructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seek.seekchallenge.infraestructure.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

   @Query("SELECT c FROM Candidate c WHERE UPPER(c.email) = UPPER(:email)")
   Optional<Candidate> findCandidateByEmail(final String email);

   @Query("SELECT c FROM Candidate c WHERE UPPER(c.email) = UPPER(:email) AND c.id <> :id")
   Optional<Candidate> findCandidateByEmailWithoutId(final String email, final Integer id);

}
