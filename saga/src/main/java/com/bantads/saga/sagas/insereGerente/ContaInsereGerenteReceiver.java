package com.bantads.saga.sagas.insereGerente;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.AuthAutoCadastroDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.service.InsereGerenteService;


@Component
public class ContaInsereGerenteReceiver {
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private InsereGerenteService aService;

    // seq 4
    @RabbitListener(queues = "saga-conta-inseregerente-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            AuthAutoCadastroDTO gerenteDTO = mapper.map(message.getData(), AuthAutoCadastroDTO.class);
            gerenteDTO.setTipo("Gerente");
            aService.setAuthIGMessage(gerenteDTO);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
}

