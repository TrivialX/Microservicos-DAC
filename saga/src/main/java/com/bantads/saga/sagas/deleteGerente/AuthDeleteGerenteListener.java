package com.bantads.saga.sagas.deleteGerente;

import java.security.NoSuchAlgorithmException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.MensagemDTO;

@Component
public class AuthDeleteGerenteListener {
    //seq 6
    @RabbitListener(queues = "saga-auth-deletegerente-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            if (message.isErro()) {
                System.out.println(message.getMensagem());
            } else {
                System.out.println("Gerente deletado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
}
