package com.bantads.saga.sagas.insereGerente;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.GerenteDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.service.InsereGerenteService;

@Component
public class GerenteInsereGerenteReceiver {
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private InsereGerenteService aService;

    // seq 2
    @RabbitListener(queues = "saga-gerente-inseregerente-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            GerenteDTO gerenteDTO = mapper.map(message.getData(), GerenteDTO.class);
            aService.setContaIGMessage(gerenteDTO);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
}


