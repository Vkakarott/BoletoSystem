package controller;

import model.Client;
import repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import dto.ClientDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<ClientDTO> listClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Validated Client client) {
        Client savedClient = clientRepository.save(client);
        ClientDTO clientDTO = new ClientDTO(savedClient);
        return new ResponseEntity<>(clientDTO, HttpStatus.CREATED);
    }
}
