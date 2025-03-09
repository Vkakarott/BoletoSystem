package com.controlticket.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controlticket.demo.dtos.InstitutionDTO;
import com.controlticket.demo.models.Institution;
import com.controlticket.demo.repositories.InstitutionRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/institutions")
public class InstitutionController {
    
    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionController(InstitutionRepository institutionRepository){ 
        this.institutionRepository = institutionRepository;
    }

    @GetMapping
    public List<InstitutionDTO> listInstituitions() {
        return institutionRepository.findAll().stream().map(InstitutionDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<InstitutionDTO> createInstitution(@RequestBody @Valid Institution institution) {
        Institution savedInstitution = institutionRepository.save(institution);
        InstitutionDTO institutionDTO = new InstitutionDTO(savedInstitution);
        return new ResponseEntity<>(institutionDTO, HttpStatus.CREATED);
    }
}
