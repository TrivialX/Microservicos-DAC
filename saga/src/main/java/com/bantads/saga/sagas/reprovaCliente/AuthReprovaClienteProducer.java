package com.bantads.saga.sagas.reprovaCliente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.SituacaoClienteDTO;

@Component
public class AuthReprovaClienteProducer {
    @Autowired
    private RabbitTemplate template;

    // Seq 3
    public void setAuthMessage(SituacaoClienteDTO dto) {
        template.convertAndSend(ConfigReprovaCliente.queueReprovaContaAuthProducer().getName(), dto);
    }
}
