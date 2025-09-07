package com.example.pagamento.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PagamentoService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public PagamentoService(RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @CircuitBreaker(name = "pedidoService", fallbackMethod = "fallbackPedido")
    @Bulkhead(name = "pedidoServiceBulkhead", type = Bulkhead.Type.THREADPOOL)
    public Map<String, Object> buscarPedido(Long pedidoId) throws Exception {
        String pedidoUrl = "http://localhost:8084/pedidos/" + pedidoId;
        String pedidoJson = restTemplate.getForObject(pedidoUrl, String.class);
        return mapper.readValue(pedidoJson, Map.class);
    }

    public Map<String, Object> fallbackPedido(Long pedidoId, Throwable t) {
        Map<String, Object> pedidoFallback = new HashMap<>();
        pedidoFallback.put("id", pedidoId);
        pedidoFallback.put("status", "Indisponível");
        return pedidoFallback;
    }
}
