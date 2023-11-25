package com.conta.DTOS;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoDTO {
    private String tipo;
    private Double value;
    private Long conta_id;
    private Long conta_destiny;
    private LocalDateTime datahora;
    private Double saldo_final_conta_id;
    private Double saldo_anterior_conta_id;
    private Double saldo_final_conta_destiny;
    private Double saldo_anterior_conta_destiny;
}
