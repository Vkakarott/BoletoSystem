package com.controlticket.demo.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.controlticket.demo.dtos.InstitutionDTO;
import com.controlticket.demo.repositories.InstitutionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class InstitutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @SuppressWarnings("unused")
    private InstitutionRepository institutionRepository;

    @Test
    void testCreateInstitution() throws Exception {
        InstitutionDTO institutionDTO = new InstitutionDTO(null, "Instituto de tecnologia", "12345678910", "123456789", "institutoTec@gmail.com");

        String institutionJson = objectMapper.writeValueAsString(institutionDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/institutions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(institutionJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Instituto de tecnologia"))
                .andExpect(jsonPath("$.cnpj").value("12345678910"))
                .andExpect(jsonPath("$.phone").value("123456789"))
                .andExpect(jsonPath("$.email").value("institutoTec@gmail.com"));
    }
}
