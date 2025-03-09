package com.controlticket.demo.dtos;

import com.controlticket.demo.models.Institution;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class InstitutionDTO {
    private Long id;
    private String name;
    private String cnpj;
    private String phone;
    private String email;

    public InstitutionDTO(Institution institution) {
        this.id = institution.getId();
        this.name = institution.getName();
        this.cnpj = institution.getCnpj();
        this.phone = institution.getPhone();
        this.email = institution.getEmail();
    }
}
