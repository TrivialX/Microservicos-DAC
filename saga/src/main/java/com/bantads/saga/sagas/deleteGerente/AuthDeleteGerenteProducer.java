package com.bantads.saga.sagas.deleteGerente;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.IdMensagemDTO;
import com.bantads.saga.sagas.insereGerente.ConfigInsereGerente;

public class AuthDeleteGerenteProducer {
    
    @Autowired
    private AmqpTemplate template;
    
    //Seq 5
    public void setAuthDeleteGerenteMessage(IdMensagemDTO dto) {
        template.convertAndSend(ConfigInsereGerente.queueAuthInsereGerente().getName(), dto);
    }
    
}
