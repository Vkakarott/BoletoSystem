package dto;

import model.Ticket;
import model.TicketStatus;
import lombok.*;

import java.time.LocalDate;

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