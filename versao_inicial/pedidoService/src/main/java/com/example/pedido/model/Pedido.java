package com.example.pedido.model;

public class Pedido {
    private Long id;
    private Long usuarioId;
    private String produto;
    private Double valor;

    public Pedido() {
    }

    public Pedido(Long id, Long usuarioId, String produto, Double valor) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.produto = produto;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
