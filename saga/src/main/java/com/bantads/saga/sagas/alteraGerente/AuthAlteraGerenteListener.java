package com.bantads.saga.sagas.alteraGerente;

import java.security.NoSuchAlgorithmException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.bantads.saga.DTO.MensagemDTO;

public class AuthAlteraGerenteListener {
    //seq 4
     @RabbitListener(queues = "saga-auth-alteragerente-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            // AuthAutoCadastroDTO Authcadastro = mapper.map(message.getData(),
            // AuthAutoCadastroDTO.class);
            // email
            System.out.println(message.getMensagem());
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
}
