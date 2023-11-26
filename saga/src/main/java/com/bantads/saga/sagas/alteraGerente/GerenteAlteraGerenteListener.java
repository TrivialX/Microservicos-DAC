package com.bantads.saga.sagas.alteraGerente;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.GerenteDTO;
import com.bantads.saga.DTO.MensagemDTO;

@Component
public class GerenteAlteraGerenteListener {

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    AuthAlteraGerenteProducer prod;

    // seq 2
    @RabbitListener(queues = "saga-gerente-alteragerente-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            GerenteDTO gerente = mapper.map(message.getData(), GerenteDTO.class);
            MensagemDTO msg = new MensagemDTO();
            msg.setMensagem("");
            msg.setData(gerente);
            prod.setAuthMessage(msg);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
}
