package com.controlticket.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.controlticket.demo.dtos.ClientDTO;
import com.controlticket.demo.models.Client;
import com.controlticket.demo.repositories.ClientRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<ClientDTO> listClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid Client client) {
        Client savedClient = clientRepository.save(client);
        ClientDTO clientDTO = new ClientDTO(savedClient);
        return new ResponseEntity<>(clientDTO, HttpStatus.CREATED);
    }
}
