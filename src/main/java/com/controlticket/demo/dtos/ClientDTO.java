package com.controlticket.demo.dtos;

import com.controlticket.demo.enums.DocumentType;
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
    private DocumentType documentType;
    private String document;
    private String phone;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.documentType = client.getDocumentType();
        this.document = client.getDocument();
        this.phone = client.getPhone();
    }
}
