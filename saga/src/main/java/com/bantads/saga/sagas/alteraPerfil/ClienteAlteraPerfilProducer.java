package com.bantads.saga.sagas.alteraPerfil;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.MensagemDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteAlteraPerfilProducer {
    
    @Autowired
    private RabbitTemplate template;
    
    //Seq 1
    public void initAlteraPerfil(MensagemDTO dto) {
        template.convertAndSend(ConfigAlteraPerfil.queueAlteraPerfilCliente().getName(), dto);
    }
}
