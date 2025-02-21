package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import dto.TicketDTO;

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

    @Column(nullable = false)
    private LocalDate dateEmiter;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Ticket(TicketDTO ticketDTO) {
        this.id = ticketDTO.getId();
        this.code = ticketDTO.getCode();
        this.value = ticketDTO.getValue();
        this.dateEmiter = ticketDTO.getDateEmiter();
        this.dueDate = ticketDTO.getDueDate();
        this.status = ticketDTO.getStatus();
    }
}
