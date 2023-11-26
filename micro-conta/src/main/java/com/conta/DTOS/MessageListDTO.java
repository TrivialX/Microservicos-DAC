package com.conta.DTOS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

import com.conta.models.CUD.ContaCUD;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageListDTO implements Serializable{
    private String mensagem;
    private List <ContaCUD> data;
    private boolean erro;
}
    

