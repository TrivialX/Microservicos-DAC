package com.bantads.saga.sagas.autoCadastro;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.MensagemDTO;

@Component
public class ClienteAutoCadastroProducer {
    @Autowired
    private AmqpTemplate template;
    
    //Seq 1
    public void initAutoCadastro(MensagemDTO dto) {
        template.convertAndSend(ConfigAutoCadastro.queueAutoCadCliente().getName(), dto);
    }
}
