package com.bantads.saga.sagas.autoCadastro;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.AuthAutoCadastroDTO;
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
            AuthAutoCadastroDTO Authcadastro = mapper.map(message.getData(), AuthAutoCadastroDTO.class);
            aService.setAuthAutoCadastro(Authcadastro);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
}
