package com.examlple.gateway;

import com.example.gateway.controller.GatewayController;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GatewayControllerTest {

    @Test
    void fallbackUsuarioDeveSerAcionado() {
        Long id = 1L;
        RestTemplate restTemplate = mock(RestTemplate.class);
        GatewayController controller = new GatewayController();
        controller.setRestTemplate(restTemplate);

        when(restTemplate.getForObject("http://localhost:8083/usuarios/" + id, String.class))
            .thenThrow(new RuntimeException("Falha simulada"));

        String resultado = controller.fallbackUsuario(id, new RuntimeException());
        assertEquals("{\"id\":1,\"status\":\"Usuário indisponível\"}", resultado);
    }
}
