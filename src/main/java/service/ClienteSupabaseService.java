package service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteSupabaseService {
    private static final String SUPABASE_URL = "https://darygudwxbxnprrlnxtz.supabase.co";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRhcnlndWR3eGJ4bnBycmxueHR6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDAxNDUwOTAsImV4cCI6MjA1NTcyMTA5MH0.qmN4GWo2XNtE_4PeXd_qEanPObH-imXigjjNm9Wjha8";

    public String listarClientes() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", API_KEY);
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                SUPABASE_URL + "/clientes", HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}

