package com.example.pagamento;

import com.example.pagamento.service.PagamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PagamentoServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ObjectMapper objectMapper;

    @Test
    void fallbackPedidoDeveSerAcionado() throws Exception {
        PagamentoService service = new PagamentoService(restTemplate, objectMapper);

        Long pedidoId = 1L;
        String resultadoJson = "{\"id\":1,\"status\":\"Indisponível\"}";

        when(restTemplate.getForObject("http://localhost:8084/pedidos/" + pedidoId, String.class))
            .thenThrow(new RuntimeException("Falha simulada"));

        Map<String, Object> resultado = service.fallbackPedido(pedidoId, new RuntimeException());

        assertEquals("Indisponível", resultado.get("status"));
        assertEquals(pedidoId, resultado.get("id"));
    }
}
