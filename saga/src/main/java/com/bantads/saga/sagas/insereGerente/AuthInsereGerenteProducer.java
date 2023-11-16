package com.bantads.saga.sagas.insereGerente;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.MensagemDTO;

@Component
public class AuthInsereGerenteProducer {
    @Autowired
    private AmqpTemplate template;
    
    //Seq 5
    public void setAuthInsereGerenteMessage(MensagemDTO dto) {
        template.convertAndSend(ConfigInsereGerente.queueAuthInsereGerente().getName(), dto);
    }

    
}
