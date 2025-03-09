package com.controlticket.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlticket.demo.dtos.InstitutionDTO;
import com.controlticket.demo.models.Institution;
import com.controlticket.demo.repositories.InstitutionRepository;

@Service
public class InstitutionService {
    
    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<InstitutionDTO> getAllInstitution() {
        return institutionRepository.findAll().stream()
                .map(InstitutionDTO::new)
                .collect(Collectors.toList());
    }

    public InstitutionDTO createInstitution(InstitutionDTO institutionDTO) {
        Institution institution = new Institution();
        institution.setName(institutionDTO.getName());
        institution.setCnpj(institutionDTO.getCnpj());
        institution.setPhone(institutionDTO.getPhone());
        institution.setEmail(institutionDTO.getEmail());

        Institution savedInstitution = institutionRepository.save(institution);
        return new InstitutionDTO(savedInstitution);
    }
}
