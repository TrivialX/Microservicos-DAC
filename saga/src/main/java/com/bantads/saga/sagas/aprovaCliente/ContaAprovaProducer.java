package com.bantads.saga.sagas.aprovaCliente;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.MensagemDTO;
import org.springframework.stereotype.Component;

@Component
public class ContaAprovaProducer {

    @Autowired
    private RabbitTemplate template;

    // seq 1
    public void setContaMessage(MensagemDTO dto) {
        template.convertAndSend(ConfigAprovaCliente.queueAprovaContaProducer().getName(), dto);
    }

}
