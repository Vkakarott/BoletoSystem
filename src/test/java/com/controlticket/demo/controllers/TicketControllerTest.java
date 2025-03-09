package com.controlticket.demo.controllers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.controlticket.demo.dtos.TicketDTO;
import com.controlticket.demo.enums.TicketStatus;
import com.controlticket.demo.models.Client;
import com.controlticket.demo.models.Institution;
import com.controlticket.demo.repositories.TicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("unused")
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void testCreateTicket() throws Exception {
        Client payMaster = new Client();
        payMaster.setId(2L);

        Institution beneficiary = new Institution();
        beneficiary.setId(4L);

        TicketDTO ticketDTO = new TicketDTO(null, null, 200.00, LocalDate.of(2025, 3, 9), null, TicketStatus.OPEN, payMaster, beneficiary);

        mockMvc.perform(MockMvcRequestBuilders.post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.code").exists())
                .andExpect(jsonPath("$.status").value("OPEN"));
    }
}
