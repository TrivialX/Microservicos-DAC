package com.bantads.saga.sagas.autoCadastro;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.MensagemDTO;

@Component
public class ContaAutoCadastroProducer {
       @Autowired
    private RabbitTemplate template;
    
    //Seq 3
    public void setContaMessage(MensagemDTO dto) {
        template.convertAndSend("saga-conta-autocadastro-init", dto);
    }
    
}
