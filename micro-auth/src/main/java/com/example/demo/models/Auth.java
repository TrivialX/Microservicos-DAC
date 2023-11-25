package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("auths")
public class Auth {

    @Id
    private String id;

    private String nome;

    private String email;

    private String senha;

    private String salt;

    private String tipoUser;

    private Long usuario;


    public Auth(String nome, String email, String senha, String salt, String tipoUser, Long idUser ) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.salt = salt;
        this.tipoUser = tipoUser;
        this.usuario = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long idUser) {
        this.usuario = idUser;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
