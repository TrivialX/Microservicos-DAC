package com.bantads.saga.DTO;

public class ContaDTO {
    private Long id;
    private Long id_cliente;
    private Long gerenteId;
    private Double saldo;
    private Double limite;
    private String situacao;
    private String observacao;

    public ContaDTO() {
    }

    public ContaDTO(Long id, Long id_cliente, Long gerenteId, Double saldo, Double limite, String situacao,
            String observacao) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.gerenteId = gerenteId;
        this.saldo = saldo;
        this.limite = limite;
        this.situacao = situacao;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Long getGerenteId() {
        return gerenteId;
    }

    public void setGerenteId(Long gerenteId) {
        this.gerenteId = gerenteId;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
