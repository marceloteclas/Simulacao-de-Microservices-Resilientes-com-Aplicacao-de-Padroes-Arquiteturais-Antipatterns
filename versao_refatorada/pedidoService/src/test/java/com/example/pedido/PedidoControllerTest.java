package com.example.pedido;

import com.example.pedido.controller.PedidoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private PedidoController controller;

    @Test
    void fallbackUsuarioRetornaIndisponivel() {
        ResponseEntity<?> resposta = controller.fallbackUsuario(1L, new RuntimeException());
        assertTrue(resposta.getBody().toString().contains("Indisponível"));
    }
}
