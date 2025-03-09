package com.controlticket.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.controlticket.demo.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    @Query("SELECT t FROM Ticket t WHERE t.payMaster.id = :payMasterId")
    List<Ticket> findByPayMaster(@Param("payMasterId") Long payMasterId);

    @Query("SELECT t FROM Ticket t WHERE t.beneficiary.id = :beneficiaryId")
    List<Ticket> findByBeneficiary(@Param("beneficiaryId") Long beneficiaryId);
}
