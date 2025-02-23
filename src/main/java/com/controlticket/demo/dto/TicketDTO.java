package com.controlticket.demo.dto;

import lombok.*;

import java.time.LocalDate;

import com.controlticket.demo.model.Ticket;
import com.controlticket.demo.model.TicketStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TicketDTO {
    private Long id;
    private String code;
    private double value;
    private LocalDate dateEmiter;
    private LocalDate dueDate;
    private TicketStatus status;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.code = ticket.getCode();
        this.value = ticket.getValue();
        this.dateEmiter = ticket.getDateEmiter();
        this.dueDate = ticket.getDueDate();
        this.status = ticket.getStatus();
    }
}