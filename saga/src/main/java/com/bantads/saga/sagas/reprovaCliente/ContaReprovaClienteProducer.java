package com.bantads.saga.sagas.reprovaCliente;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.MensagemDTO;


public class ContaReprovaClienteProducer {
    @Autowired
    private RabbitTemplate template;

    public void setContaMessage(MensagemDTO dto) {
        template.convertAndSend(ConfigReprovaCliente.queueReprovaContaProducer().getName(), dto);
    }
}
