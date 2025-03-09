package com.controlticket.demo.dtos;

import lombok.*;

import java.time.LocalDate;

import com.controlticket.demo.enums.TicketStatus;
import com.controlticket.demo.models.Ticket;
import com.controlticket.demo.models.Client;
import com.controlticket.demo.models.Institution;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TicketDTO {
    private Long id;
    private String code;
    private double value;
    private LocalDate dateEmitter;
    private LocalDate dueDate;
    private TicketStatus status;
    private Client payMaster;
    private Institution beneficiary;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.code = ticket.getCode();
        this.value = ticket.getValue();
        this.dateEmitter = ticket.getDateEmitter();
        this.dueDate = ticket.getDueDate();
        this.status = ticket.getStatus();
        this.payMaster = ticket.getPayMaster();
        this.beneficiary = ticket.getBeneficiary();
    }
}