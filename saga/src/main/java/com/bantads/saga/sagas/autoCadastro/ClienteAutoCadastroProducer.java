package com.bantads.saga.sagas.autoCadastro;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.MensagemDTO;

@Component
public class ClienteAutoCadastroProducer {
    @Autowired
    private RabbitTemplate template;
    
    //Seq 1
    public void initAutoCadastro(MensagemDTO dto) {
        template.convertAndSend("saga-cliente-autocadastro-init", dto);
    }
}
