package com.conta.models.CUD;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
@Entity
@Table(name="tbl_movimentacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacoesCUD {
    @Id
    private Long id;
    private String type;
    private Double value;
    private Long conta_id;
    private Long conta_destiny;
    private Date dataHora;
    private Double saldo_final;
    private Double saldo_anterior;
}
