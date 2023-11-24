package com.bantads.saga.sagas.deleteGerente;

import java.security.NoSuchAlgorithmException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.IdMensagemDTO;

import com.bantads.saga.service.DeleteGerenteService;


@Component
public class GerenteDeleteGerenteListener {
    
    @Autowired
    private DeleteGerenteService aService;

    // seq 2
    @RabbitListener(queues = "saga-gerente-deletegerente-end")
    public void receiveMessageSaga(@Payload IdMensagemDTO message) throws NoSuchAlgorithmException {
        try {
            Long id = message.getId();
            aService.setContaDGMessage(id);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
    
}
