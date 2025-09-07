package com.example.usuario.controller;

import com.example.usuario.model.Usuario;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private Map<Long, Usuario> usuarios = new HashMap<>();

    @PostConstruct
    public void init() {
        usuarios.put(1L, new Usuario(1L, "Marcelo Silva", "marcelo@example.com"));
        usuarios.put(2L, new Usuario(2L, "Ronaldo Souza", "ronaldo@example.com"));
    }

    @GetMapping
    public Map<Long, Usuario> listarUsuarios() {
        return usuarios;
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarios.get(id);
    }
}
