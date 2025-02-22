package dto;

import model.Client;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phone;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.cpf = client.getCpf();
        this.phone = client.getPhone();
    }
}
