package com.conta.DTOS;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaSagaDTO implements Serializable {

    private Long id_cliente;
    private Long gerente_id;
    private Double limite;

}
