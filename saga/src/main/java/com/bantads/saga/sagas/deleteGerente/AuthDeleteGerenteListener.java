package com.bantads.saga.sagas.deleteGerente;

import java.security.NoSuchAlgorithmException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.bantads.saga.DTO.MensagemDTO;

public class AuthDeleteGerenteListener {
    
    @RabbitListener(queues = "saga-auth-inseregerente-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            if (message.isErro()) {
                System.out.println(message.getMensagem());
            } else {
                System.out.println("Gerente Criado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
}
