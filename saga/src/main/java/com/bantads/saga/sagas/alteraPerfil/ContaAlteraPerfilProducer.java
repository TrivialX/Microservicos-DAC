package com.bantads.saga.sagas.alteraPerfil;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.MensagemDTO;
import org.springframework.stereotype.Component;

@Component
public class ContaAlteraPerfilProducer {
     @Autowired
    private RabbitTemplate template;
    
    //Seq 3
    public void setContaMessage(MensagemDTO dto) {
        template.convertAndSend(ConfigAlteraPerfil.queueAlteraPerfilConta().getName(), dto);
    }
    
}
