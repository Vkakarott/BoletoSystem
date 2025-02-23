package com.controlticket.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.controlticket.demo.dto.TicketDTO;
import com.controlticket.demo.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketControler {
    
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<TicketDTO> listTickets() {
        return ticketService.listTickets();
    }

    @PostMapping
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.createTicket(ticketDTO);
    }
}
