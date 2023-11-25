package com.example.demo.DTOS;

public class AuthDTO {

    private String email;
    private String senha;
    private String nome;
    private Long id_usuario;

    public AuthDTO(String email, String senha,String nome, Long id_usuario) {
        this.email = email;
        this.senha = senha;
        this.nome= nome;
        this.id_usuario =  id_usuario;;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
}
