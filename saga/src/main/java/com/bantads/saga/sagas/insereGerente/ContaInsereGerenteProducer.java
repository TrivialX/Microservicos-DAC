package com.bantads.saga.sagas.insereGerente;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.MensagemDTO;

@Component
public class ContaInsereGerenteProducer {

    @Autowired
    private AmqpTemplate template;

    // Seq 3
    public void setContaInsereGerenteMessage(MensagemDTO dto) {
        template.convertAndSend(ConfigInsereGerente.queueContaInsereGerente().getName(), dto);
    }

}
