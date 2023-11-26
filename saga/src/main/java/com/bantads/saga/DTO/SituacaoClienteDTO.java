package com.bantads.saga.DTO;

public class SituacaoClienteDTO {
    private Long id_cliente;
    private String situacao;

    public SituacaoClienteDTO(Long id_cliente, String situacao) {
        this.id_cliente = id_cliente;
        this.situacao = situacao;
    }

    public SituacaoClienteDTO() {
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
