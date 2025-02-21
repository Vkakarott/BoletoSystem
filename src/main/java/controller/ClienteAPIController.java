package controller;

import service.ClienteSupabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class ClienteAPIController {

    @Autowired
    private ClienteSupabaseService clienteSupabaseService;

    @GetMapping("/clientes")
    public String getClientesFromSupabase() {
        return clienteSupabaseService.listarClientes();
    }
}
