package com.example.CodeChallenge.institution.services;

import com.example.CodeChallenge.institution.models.Institution;
import java.util.List;
import java.util.Optional;

public interface InstitutionService {
    Optional<Institution> getInstitution(Long id);
    List<Institution> getAllInstitutions();
    List<Institution> getInstitutionsByStatus(String status);
    Institution saveInstitution(Institution institution);
    void deleteInstitution(Long id);
}
