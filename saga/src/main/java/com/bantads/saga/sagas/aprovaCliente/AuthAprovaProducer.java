package com.bantads.saga.sagas.aprovaCliente;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.SituacaoClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthAprovaProducer {
    @Autowired
    private RabbitTemplate template;

    // Seq 3
    public void setAuthMessage(SituacaoClienteDTO dto) {
        template.convertAndSend(ConfigAprovaCliente.queueAprovaContaAuthProducer().getName(), dto);
    }
}
