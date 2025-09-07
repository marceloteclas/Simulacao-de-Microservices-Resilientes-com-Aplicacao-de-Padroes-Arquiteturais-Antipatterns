package com.example.pagamento.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.pagamento.model.Pagamento;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private Map<Long, Pagamento> pagamentos = new HashMap<>();
    private Long nextId = 1L;

    @PostConstruct
    public void init() {
        pagamentos.put(nextId, new Pagamento(nextId++, 1L, 49.90));
    }

    @GetMapping
    public Map<Long, Pagamento> listarPagamentos() {
        return pagamentos;
    }

    @PostMapping
    public ResponseEntity<?> criarPagamento(@RequestBody Pagamento pagamento) {
        pagamento.setId(nextId++);
        pagamentos.put(pagamento.getId(), pagamento);

        String pedidoUrl = "http://localhost:8082/pedidos/" + pagamento.getPedidoId();
        RestTemplate rest = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String pedidoJson = rest.getForObject(pedidoUrl, String.class);
            Map<String, Object> pedidoMap = mapper.readValue(pedidoJson, Map.class);

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("pagamento", pagamento);
            resposta.put("pedido", pedidoMap);

            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao acessar pedido-service: " + e.getMessage());
        }
    }

}
