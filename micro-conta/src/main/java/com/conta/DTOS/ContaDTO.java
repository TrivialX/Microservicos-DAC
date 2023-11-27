package com.conta.DTOS;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    private Long id;

    private Long id_cliente;

    private Long gerenteId;

    private Double saldo;

    private Double limite;

    private String situacao;

    private String observacao;
}
