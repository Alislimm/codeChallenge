package com.example.CodeChallenge.institution.services.impl;

import com.example.CodeChallenge.institution.models.Institution;
import com.example.CodeChallenge.institution.repositories.InstitutionRepository;
import com.example.CodeChallenge.institution.services.InstitutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@Slf4j
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Optional<Institution> getInstitution(Long id) {
        log.info("Fetching institution with id: {}", id);
        return institutionRepository.findById(id);
    }

    @Override
    public List<Institution> getAllInstitutions() {
        log.info("Fetching all institutions");
        return institutionRepository.findAll();
    }

    @Override
    public List<Institution> getInstitutionsByStatus(String status) {
        log.info("Fetching institutions by status: {}", status);
        return institutionRepository.findAllByStatus(status);
    }

    @Override
    public Institution saveInstitution(Institution institution) {
        log.info("Saving institution: {}", institution);
        if ("0".equals(institution.getStatus())) {
            log.info("Creating new institution");
            return institutionRepository.save(institution);
        } else if ("1".equals(institution.getStatus())) {
            log.info("Updating existing institution with id: {}", institution.getId());
            Optional<Institution> optionalOldInstitution = institutionRepository.findById(institution.getId());
            if (optionalOldInstitution.isPresent()) {
                Institution oldInstitution = optionalOldInstitution.get();
                if (institution.getCode() != null) {
                    oldInstitution.setCode(institution.getCode());
                }
                if (institution.getName() != null) {
                    oldInstitution.setName(institution.getName());
                }
                return institutionRepository.save(oldInstitution);
            } else {
                throw new IllegalArgumentException("Institution with id " + institution.getId() + " not found");
            }
        } else {
            throw new IllegalArgumentException("Invalid status: " + institution.getStatus());
        }
    }

    @Override
    public void deleteInstitution(Long id) {
        log.info("Deleting institution with id: {}", id);
        institutionRepository.deleteById(id);
    }
}