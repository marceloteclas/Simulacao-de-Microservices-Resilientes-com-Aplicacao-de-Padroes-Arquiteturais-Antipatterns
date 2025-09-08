package com.example.pedido.controller;

import com.example.pedido.model.Pedido;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private Map<Long, Pedido> pedidos = new HashMap<>();
    private Long nextId = 1L;
    private final RestTemplate restTemplate;

    public PedidoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        pedidos.put(nextId, new Pedido(nextId++, 1L, "Camiseta", 49.90));
    }

    @GetMapping
    public Map<Long, Pedido> listarPedidos() {
        return pedidos;
    }

    @CircuitBreaker(name = "usuarioService", fallbackMethod = "fallbackUsuario")
    @Bulkhead(name = "usuarioBulkhead", type = Bulkhead.Type.THREADPOOL)
    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoComUsuario(@PathVariable Long id) {
        Pedido p = pedidos.get(id);
        if (p == null)
            return ResponseEntity.notFound().build();

        String usuarioUrl = "http://localhost:8083/usuarios/" + p.getUsuarioId();
        String usuarioJson = restTemplate.getForObject(usuarioUrl, String.class);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("pedido", p);
        resposta.put("usuario", usuarioJson);
        return ResponseEntity.ok(resposta);
    }

    public ResponseEntity<?> fallbackUsuario(Long id, Throwable t) {
        Pedido p = pedidos.get(id);
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("pedido", p);
        resposta.put("usuario", "Indisponível");
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        pedido.setId(nextId++);
        pedidos.put(pedido.getId(), pedido);
        return ResponseEntity.ok(pedido);
    }
}
