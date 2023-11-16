package com.bantads.saga.sagas.deleteGerente;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.IdMensagemDTO;

public class ContaDeleteGerenteProducer {

     @Autowired
    private AmqpTemplate template;
    
    //Seq 3
    public void setContaDeleteGerenteMessage(IdMensagemDTO dto) {
        template.convertAndSend(ConfigDeleteGerente.queueContaDeleteGerente().getName(), dto);
    }

    
}
