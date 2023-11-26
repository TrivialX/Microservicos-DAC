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

    private String nome;

    private String cpf;
    private String logradouro;
    private String numero;
    private String complemento;
    private String tipo;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone;
    private double salario;


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

    public AutoCadastroDTO(Long id_cliente, Long id_gerente, Double saldo, Double limite, String situacao, String observacao, String email, String senha, String nome, String cpf, String logradouro, String numero, String complemento, String tipo, String cidade, String estado, String cep, String telefone, double salario) {
        this.id_cliente = id_cliente;
        this.id_gerente = id_gerente;
        this.saldo = saldo;
        this.limite = limite;
        this.situacao = situacao;
        this.observacao = observacao;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.tipo = tipo;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
        this.salario = salario;
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


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
