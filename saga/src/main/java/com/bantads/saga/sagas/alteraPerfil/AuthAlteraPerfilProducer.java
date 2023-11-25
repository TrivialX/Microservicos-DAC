package com.bantads.saga.sagas.alteraPerfil;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.MensagemDTO;

import org.springframework.stereotype.Component;

@Component
public class AuthAlteraPerfilProducer {
    
    @Autowired
    private RabbitTemplate template;

    // Seq 5
    public void setAuthMessage(MensagemDTO dto) {
        template.convertAndSend(ConfigAlteraPerfil.queueAlteraPerfilAuth().getName(), dto);
    }
}
