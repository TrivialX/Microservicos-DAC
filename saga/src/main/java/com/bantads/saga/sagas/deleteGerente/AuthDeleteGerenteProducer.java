package com.bantads.saga.sagas.deleteGerente;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.IdMensagemDTO;


@Component
public class AuthDeleteGerenteProducer {
    
    @Autowired
    private AmqpTemplate template;
    
    //Seq 5
    public void setAuthDeleteGerenteMessage(IdMensagemDTO dto) {
        template.convertAndSend(ConfigDeleteGerente.queueAuthDeleteGerente().getName(), dto);
    }
    
}
