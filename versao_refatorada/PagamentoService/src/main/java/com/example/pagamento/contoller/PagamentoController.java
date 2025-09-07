package com.example.pagamento.contoller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.HashMap;
import com.example.pagamento.service.PagamentoService;
import com.example.pagamento.model.Pagamento;


@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private Map<Long, Pagamento> pagamentos = new HashMap<>();
    private Long nextId = 1L;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

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

        try {
            Map<String, Object> pedidoMap = pagamentoService.buscarPedido(pagamento.getPedidoId());

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("pagamento", pagamento);
            resposta.put("pedido", pedidoMap);

            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao acessar pedido-service: " + e.getMessage());
        }
    }
}
