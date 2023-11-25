package com.bantads.saga.sagas.alteraGerente;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.MensagemDTO;

public class AuthAlteraGerenteProducer {
    
    @Autowired
    private RabbitTemplate template;
    
    //Seq 3
    public void setAuthMessage(MensagemDTO dto) {
        template.convertAndSend(ConfigAlteraGerente.queueAuthAlteraGerente().getName(), dto);
    }
}
