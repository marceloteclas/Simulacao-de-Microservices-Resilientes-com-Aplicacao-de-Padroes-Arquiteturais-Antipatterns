package com.example.gateway.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class GatewayController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/usuarios/{id}")
    public String getUsuario(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8083/usuarios/" + id, String.class);
    }

    @GetMapping("/pedidos/{id}")
    public String getPedido(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8084/pedidos/" + id, String.class);
    }
}
