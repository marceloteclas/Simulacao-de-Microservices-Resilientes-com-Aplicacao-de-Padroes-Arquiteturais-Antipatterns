package com.example.usuario.controller;

import com.example.usuario.model.Usuario;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//classe de controller com anti-patterns e violações de princípios SOLID como SRP e OCP;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // Anti-pattern: Controller guardando dados em memória
    // Viola SRP (Single Responsibility Principle) → Controller não deveria ser
    // responsável pelo "armazenamento" de dados

    private Map<Long, Usuario> usuarios = new HashMap<>();

    // Viola SRP → Controller acessa diretamente os dados
    // Viola OCP → se mudar de HashMap para banco de dados, precisa alterar o
    // Controller

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
