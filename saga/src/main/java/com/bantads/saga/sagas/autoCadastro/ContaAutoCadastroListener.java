package com.bantads.saga.sagas.autoCadastro;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.AutoCadastroDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.service.AutoCadastroService;

@Component
public class ContaAutoCadastroListener {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AutoCadastroService aService;


    //seq 4
    @RabbitListener(queues = "saga-conta-autocadastro-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            AutoCadastroDTO authACDTO = mapper.map(message.getData(), AutoCadastroDTO.class);
            aService.setAuthAutoCadastro(authACDTO);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }

}
