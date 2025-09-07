package com.example.pedido.controller;

import com.example.pedido.model.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;

//classe controller com varios anti-padrões e violações de princípios SOLID
//exemplos de anti-padrões: armazenamento em memória, alto acoplamento, controller fazendo chamadas diretas a outros serviços
// violações de princípios SOLID: SRP, DIP
// violaão god class - controller com muitas responsabilidades

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    // Viola a SRP (Single Responsibility Principle)- controller não deve funcionar
    // como repositório de dados;
    // Em um cenário real, o controller deveria delegar a responsabilidade de
    // gerenciar os pedidos

    private Map<Long, Pedido> pedidos = new HashMap<>();
    private Long nextId = 1L;

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

        // Alto acoplamento: Controller chamando diretamente outro serviço via
        // RestTemplate
        // Viola DIP (Dependency Inversion) → Controller depende de implementação
        // concreta
        // Viola SRP → Controller não deveria orquestrar integração externa

        // CHAMADA DIRETA ao usuario-service (alto acoplamento)
        String usuarioUrl = "http://localhost:8081/usuarios/" + p.getUsuarioId();
        RestTemplate rest = new RestTemplate();

        try {
            String usuarioJson = rest.getForObject(usuarioUrl, String.class);
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
