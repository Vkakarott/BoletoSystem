package com.controlticket.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlticket.demo.dto.TicketDTO;
import com.controlticket.demo.model.Ticket;
import com.controlticket.demo.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketDTO> listTickets() {
        return ticketRepository.findAll().stream().map(ticket -> new TicketDTO(ticket)).collect(Collectors.toList());
    }

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket(ticketDTO);
        ticketRepository.save(ticket);
        return new TicketDTO(ticket);
    }
}
