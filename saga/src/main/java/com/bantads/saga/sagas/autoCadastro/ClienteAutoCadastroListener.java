package com.bantads.saga.sagas.autoCadastro;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.ClienteAutoCadastroDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.service.AutoCadastroService;

@Component
public class ClienteAutoCadastroListener {
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private AutoCadastroService aService;

    // seq 2
    @RabbitListener(queues = "saga-cliente-autocadastro-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            ClienteAutoCadastroDTO clienteACDTO = mapper.map(message.getData(), ClienteAutoCadastroDTO.class);
            aService.setContaAutoCadastro(clienteACDTO);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }

}
