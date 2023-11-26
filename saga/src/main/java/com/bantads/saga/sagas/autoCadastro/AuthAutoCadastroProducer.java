package com.bantads.saga.sagas.autoCadastro;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bantads.saga.DTO.MensagemDTO;

@Component
public class AuthAutoCadastroProducer {

    @Autowired
    private RabbitTemplate template;

    // Seq 5
    public void setAuthMessage(MensagemDTO dto) {
        template.convertAndSend(ConfigAutoCadastro.queueAutoCadAuth().getName(), dto);
    }
}
