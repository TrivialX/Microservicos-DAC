package com.bantads.saga.DTO;

public class AutoCadastroDTO {
    private Long id_cliente;

    private Long id_gerente;

    private Double saldo;

    private Double limite;

    private String situacao;

    private String observacao;

    private String email;

    private String senha;

    public AutoCadastroDTO() {
    }
    

    public AutoCadastroDTO(Long id_cliente, Long id_gerente, Double saldo, Double limite, String situacao,
            String observacao, String email, String senha) {
        this.id_cliente = id_cliente;
        this.id_gerente = id_gerente;
        this.saldo = saldo;
        this.limite = limite;
        this.situacao = situacao;
        this.observacao = observacao;
        this.email = email;
        this.senha = senha;
    }


    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId_gerente() {
        return id_gerente;
    }


    public void setId_gerente(Long id_gerente) {
        this.id_gerente = id_gerente;
    }



  
}
