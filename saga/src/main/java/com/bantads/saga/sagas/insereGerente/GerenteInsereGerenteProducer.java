package com.bantads.saga.sagas.insereGerente;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.MensagemDTO;

@Component
public class GerenteInsereGerenteProducer {
     @Autowired
    private AmqpTemplate template;
    
    //Seq 1
    public void initInsereGerente(MensagemDTO dto) {
        template.convertAndSend(ConfigInsereGerente.queueInsereGerente().getName(), dto);
    }

}
    

