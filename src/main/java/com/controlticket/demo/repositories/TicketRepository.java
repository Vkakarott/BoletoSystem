package com.controlticket.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controlticket.demo.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    List<Ticket> findByPayMaster(Long payMasterId);

    List<Ticket> findByBeneficiary(Long beneficiaryId);
}
