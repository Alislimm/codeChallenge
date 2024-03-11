package com.example.CodeChallenge.institution.controllers;

import com.example.CodeChallenge.institution.models.Institution;
import com.example.CodeChallenge.institution.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/institution")
public class InstitutionController {

    private final InstitutionService institutionService;

    @Autowired
    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInstitutionById(@PathVariable("id") Long id) {
        Optional<Institution> institution = institutionService.getInstitution(id);

        if (institution.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Institution with id " + id + " not found");
        }

        return ResponseEntity.ok().body(institution);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllInstitutions() {
        List<Institution> institutions = institutionService.getAllInstitutions();

        if (institutions.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No institutions found");
        }

        return ResponseEntity.ok().body(institutions);
    }

    @GetMapping(value = "/get/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInstitutionsByStatus(@PathVariable("status") String status) {
        List<Institution> institutions = institutionService.getInstitutionsByStatus(status);

        if (institutions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No institutions found with status: " + status);
        }

        return ResponseEntity.ok().body(institutions);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveInstitution(@RequestBody Institution institution) {
        try {
            Institution savedInstitution = institutionService.saveInstitution(institution);
            return ResponseEntity.ok().body(savedInstitution);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteInstitution(@PathVariable("id") Long id) {
        institutionService.deleteInstitution(id);
        return ResponseEntity.ok().body("Institution record with id " + id + " deleted successfully.");
    }
}