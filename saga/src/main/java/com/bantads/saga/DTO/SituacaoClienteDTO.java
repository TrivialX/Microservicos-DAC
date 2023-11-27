package com.bantads.saga.DTO;

public class SituacaoClienteDTO {
    private Long id_cliente;
    private String situacao;

    private String observacao;

    public SituacaoClienteDTO(Long id_cliente, String situacao, String obersevacao) {
        this.id_cliente = id_cliente;
        this.situacao = situacao;
        this.observacao = obersevacao;
    }

    public SituacaoClienteDTO() {
    }


    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
