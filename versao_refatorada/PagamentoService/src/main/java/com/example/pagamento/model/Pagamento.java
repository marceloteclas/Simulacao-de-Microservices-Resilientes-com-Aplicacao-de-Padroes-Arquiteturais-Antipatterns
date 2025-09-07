package com.example.pagamento.model;

public class Pagamento {
    private Long id;
    private Long pedidoId;
    private Double valor;

    public Pagamento() {}

    public Pagamento(Long id, Long pedidoId, Double valor) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
}
