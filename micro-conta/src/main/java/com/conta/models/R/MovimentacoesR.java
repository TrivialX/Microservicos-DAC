package com.conta.models.R;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.util.Date;


@Table(name="tbl_movimentacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MovimentacoesR {

    @Id
    private Long id;
    private String type;
    private Double value;
    private Long conta_id;
    private Long conta_destiny;
    private Double saldo_final;
    private Date dataHora;

}
