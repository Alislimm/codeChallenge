package com.example.CodeChallenge.institution.repositories;

import com.example.CodeChallenge.institution.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Optional<Institution> findById(Long id);
    List<Institution> findAll();
    List<Institution> findAllByStatus(String status);

}
