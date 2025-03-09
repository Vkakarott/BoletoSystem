package com.controlticket.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.controlticket.demo.dtos.TicketDTO;
import com.controlticket.demo.enums.TicketStatus;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private double value;

    @Column(name = "date_emission", nullable = false)
    private LocalDate dateEmitter;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "pay_master_id", nullable = false)
    private Client payMaster;

    @ManyToOne
    @JoinColumn(name = "beneficiary_id", nullable = false)
    private Institution beneficiary;

    public Ticket(TicketDTO ticketDTO) {
        this.id = ticketDTO.getId();
        this.code = ticketDTO.getCode();
        this.value = ticketDTO.getValue();
        this.dateEmitter = ticketDTO.getDateEmitter();
        this.dueDate = ticketDTO.getDueDate();
        this.status = ticketDTO.getStatus();
        this.payMaster = ticketDTO.getPayMaster();
        this.beneficiary = ticketDTO.getBeneficiary();
    }
}
