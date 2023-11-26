package com.bantads.saga.sagas.autoCadastro;

import java.security.NoSuchAlgorithmException;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.modelmapper.ModelMapper;
import org.springframework.messaging.handler.annotation.Payload;

//import com.bantads.saga.DTO.AuthAutoCadastroDTO;
import com.bantads.saga.DTO.MensagemDTO;
//import com.bantads.saga.service.AutoCadastroService;
import org.springframework.stereotype.Component;

@Component
public class AuthAutoCadastroListener {

    /*
     * @Autowired
     * private ModelMapper mapper;
     * 
     * @Autowired
     * private AutoCadastroService aService;
     */

    // seq 6
    @RabbitListener(queues = "saga-auth-autocadastro-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            // AuthAutoCadastroDTO Authcadastro = mapper.map(message.getData(),
            // AuthAutoCadastroDTO.class);
            // email
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }

}
