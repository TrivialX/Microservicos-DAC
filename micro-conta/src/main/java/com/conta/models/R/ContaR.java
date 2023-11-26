package com.conta.models.R;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_conta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_cliente")
    private Long id_cliente;

    @Column(name = "gerente_id")
    private Long gerente_id;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "limite")
    private Double limite;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "observacao")
    private String observacao;
}
