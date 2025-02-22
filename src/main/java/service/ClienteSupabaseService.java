package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClienteSupabaseService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.apikey}")
    private String apiKey;

    private final WebClient webClient;

    public ClienteSupabaseService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(supabaseUrl).build();
    }

    public Mono<String> listarClientes() {
        return webClient.get()
                .uri("/clientes")
                .header("apikey", apiKey) 
                .header("Authorization", "Bearer " + apiKey) 
                .retrieve()
                .onStatus(status -> status.isError(), response -> {
                    return Mono.error(new RuntimeException("Erro ao acessar clientes"));
                })
                .bodyToMono(String.class);  
    }
}
