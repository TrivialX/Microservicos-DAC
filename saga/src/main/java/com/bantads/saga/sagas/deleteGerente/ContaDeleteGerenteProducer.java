package com.bantads.saga.sagas.deleteGerente;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.IdMensagemDTO;

@Component
public class ContaDeleteGerenteProducer {

     @Autowired
    private RabbitTemplate template;
    
    //Seq 3
    public void setContaDeleteGerenteMessage(IdMensagemDTO dto) {
        template.convertAndSend(ConfigDeleteGerente.queueContaDeleteGerente().getName(), dto);
    }

    
}
