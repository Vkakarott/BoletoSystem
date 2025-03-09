package com.controlticket.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlticket.demo.models.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    
    Optional<Institution> findByEmail(String email);

    List<Institution> findByNameIgnoreCase(String name);

    List<Institution> findByNameContainingIgnoreCase(String name);
}
