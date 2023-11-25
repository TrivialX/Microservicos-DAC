package com.conta.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutocadastroDTO implements Serializable {

    private Long id_cliente;

    private Long id_gerente;

    private Double saldo;

    private Double limite;

    private String situacao;

    private String observacao;

    private String email;

    private String senha;

}
