package com.controlticket.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlticket.demo.dtos.ClientDTO;
import com.controlticket.demo.models.Client;
import com.controlticket.demo.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setCpf(clientDTO.getCpf());
        client.setPhone(clientDTO.getPhone());

        Client savedClient = clientRepository.save(client);
        return new ClientDTO(savedClient);
    }
}
