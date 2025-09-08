package com.example.gateway.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GatewayController {

    @Autowired
    private RestTemplate restTemplate;
    public void setRestTemplate(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
}


    @CircuitBreaker(name = "usuarioService", fallbackMethod = "fallbackUsuario")
    @Bulkhead(name = "usuarioBulkhead", type = Bulkhead.Type.THREADPOOL)
    @GetMapping("/usuarios/{id}")
    public String getUsuario(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8083/usuarios/" + id, String.class);
    }

    @CircuitBreaker(name = "pedidoService", fallbackMethod = "fallbackPedido")
    @Bulkhead(name = "pedidoBulkhead", type = Bulkhead.Type.THREADPOOL)
    @GetMapping("/pedidos/{id}")
    public String getPedido(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8084/pedidos/" + id, String.class);
    }

    public String fallbackUsuario(Long id, Throwable t) {
        return "{\"id\":" + id + ",\"status\":\"Usuário indisponível\"}";
    }

    public String fallbackPedido(Long id, Throwable t) {
        return "{\"id\":" + id + ",\"status\":\"Pedido indisponível\"}";
    }
}
