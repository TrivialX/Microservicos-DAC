package com.bantads.saga.sagas.alteraGerente;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.MensagemDTO;

@Component
public class GerenteAlteraGerenteProducer {
    @Autowired
    private RabbitTemplate template;
    
    //Seq 1
    public void initAlteraGerente(MensagemDTO dto) {
        template.convertAndSend(ConfigAlteraGerente.queueAlteraGerente().getName(), dto);
    }


}

