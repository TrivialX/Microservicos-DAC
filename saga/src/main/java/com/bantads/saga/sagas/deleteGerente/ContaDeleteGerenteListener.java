package com.bantads.saga.sagas.deleteGerente;

import java.security.NoSuchAlgorithmException;

//import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.IdMensagemDTO;
import com.bantads.saga.service.DeleteGerenteService;


@Component
public class ContaDeleteGerenteListener {
        
   /*  @Autowired
    private ModelMapper mapper;*/
    @Autowired
    private DeleteGerenteService aService;

    // seq 4
    @RabbitListener(queues = "saga-conta-deletegerente-end")
    public void receiveMessageSaga(@Payload IdMensagemDTO message) throws NoSuchAlgorithmException {
        try {
            Long id = message.getId();
            aService.setAuthDGMessage(id);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
}
