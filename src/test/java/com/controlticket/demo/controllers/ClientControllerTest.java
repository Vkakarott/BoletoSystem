package com.controlticket.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.controlticket.demo.dtos.ClientDTO;
import com.controlticket.demo.repositories.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("unused")
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testCreateClient() throws Exception {
        ClientDTO clientDTO = new ClientDTO(null, "John Doe", "jd12345@gmail.com", "892.234.452-34", "40028922");

        String clientJson = objectMapper.writeValueAsString(clientDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("jd12345@gmail.com"))
                .andExpect(jsonPath("$.cpf").value("892.234.452-34"))
                .andExpect(jsonPath("$.phone").value("40028922"));
    }
}
