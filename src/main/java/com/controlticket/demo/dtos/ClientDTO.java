package com.controlticket.demo.dtos;

import com.controlticket.demo.models.Client;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phone;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.cpf = client.getCpf();
        this.phone = client.getPhone();
    }
}
