package com.example.demo.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutocadastroDTO {

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

}
