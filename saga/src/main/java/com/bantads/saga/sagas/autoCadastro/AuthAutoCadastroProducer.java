package com.bantads.saga.sagas.autoCadastro;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bantads.saga.DTO.MensagemDTO;


@Component
public class AuthAutoCadastroProducer {

    @Autowired
    private AmqpTemplate template;
    
    //Seq 3
    public void setAuthMessage(MensagemDTO dto) {
        template.convertAndSend(ConfigAutoCadastro.queueAutoCadAuth().getName(), dto);
    }
}
    

