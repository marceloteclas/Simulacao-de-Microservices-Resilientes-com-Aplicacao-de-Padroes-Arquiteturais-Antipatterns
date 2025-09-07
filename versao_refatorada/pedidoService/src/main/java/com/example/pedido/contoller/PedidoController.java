package com.example.pedido.contoller;

import com.example.pedido.model.Pedido;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoComUsuario(@PathVariable Long id) {
        Pedido p = pedidos.get(id);
        if (p == null)
            return ResponseEntity.notFound().build();

        String usuarioUrl = "http://localhost:8083/usuarios/" + p.getUsuarioId();

        try {
            String usuarioJson = restTemplate.getForObject(usuarioUrl, String.class);
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("pedido", p);
            resposta.put("usuario", usuarioJson);
            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao acessar usuario-service: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        pedido.setId(nextId++);
        pedidos.put(pedido.getId(), pedido);
        return ResponseEntity.ok(pedido);
    }
}