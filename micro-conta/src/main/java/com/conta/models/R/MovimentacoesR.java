package com.conta.models.R;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Date;


@Table(name="tbl_movimentacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MovimentacoesR {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "value")
    private Double value;

    @Column(name = "conta_id")
    private Long conta_id;

    @Column(name = "conta_destiny")
    private Long conta_destiny;

    @Column(name = "datahora")
    private LocalDateTime datahora;

    @Column(name = "saldo_final_conta_id")
    private Double saldo_final_conta_id;

    @Column(name = "saldo_anterior_conta_id")
    private Double saldo_anterior_conta_id;

    @Column(name = "saldo_final_conta_destiny")
    private Double saldo_final_conta_destiny;

    @Column(name = "saldo_anterior_conta_destiny")
    private Double saldo_anterior_conta_destiny;

}
