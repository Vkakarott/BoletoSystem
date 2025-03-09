package com.controlticket.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlticket.demo.dtos.TicketDTO;
import com.controlticket.demo.enums.TicketStatus;
import com.controlticket.demo.models.Ticket;
import com.controlticket.demo.repositories.TicketRepository;
import com.controlticket.demo.utils.GenerateCode;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    public Optional<Ticket> findTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<TicketDTO> listTickets() {
        return ticketRepository.findAll().stream()
            .map(ticket -> new TicketDTO(ticket))
            .collect(Collectors.toList());
    }

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        LocalDate dateEmitter = (ticketDTO.getDateEmitter() != null) ? ticketDTO.getDateEmitter() : LocalDate.now();
        LocalDate dueDate = dateEmitter.plusDays(15);

        if (ticketDTO.getPayMaster() == null || ticketDTO.getPayMaster().getId() == null) {
            throw new IllegalArgumentException("O pagador (payMaster) é obrigatório e precisa ter um ID válido.");
        }
        if (ticketDTO.getBeneficiary() == null || ticketDTO.getBeneficiary().getId() == null) {
            throw new IllegalArgumentException("O beneficiário (beneficiary) é obrigatório e precisa ter um ID válido.");
        }

        Ticket ticket = new Ticket();
        ticket.setCode(GenerateCode.generateLine(ticketDTO.getBeneficiary().getId().toString(), ticketDTO.getPayMaster().getId(), dueDate, ticketDTO.getValue()));
        ticket.setValue(ticketDTO.getValue());
        ticket.setDateEmitter(ticketDTO.getDateEmitter());
        ticket.setDueDate(dueDate);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setPayMaster(ticketDTO.getPayMaster());
        ticket.setBeneficiary(ticketDTO.getBeneficiary());

        ticket = ticketRepository.save(ticket);
        
        return new TicketDTO(ticket);
    }
}
