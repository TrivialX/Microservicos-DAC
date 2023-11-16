package com.bantads.saga.DTO;

public class EnderecoDTO {
    private String logradouro;
    private String numero;
    private String complemento;
    private String tipo;
    private String cidade;
    private String estado;
    private String cep;

    public EnderecoDTO(String logradouro, String numero, String complemento, String tipo, String cidade, String estado,
            String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.tipo = tipo;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public EnderecoDTO() {
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

    public String getlogradouro() {
        return logradouro;
    }

    public void setlogradouro(String logradouro) {
        this.logradouro = logradouro;
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
}
