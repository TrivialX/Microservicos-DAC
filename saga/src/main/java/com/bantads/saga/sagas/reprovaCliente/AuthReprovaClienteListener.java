package com.bantads.saga.sagas.reprovaCliente;

import java.security.NoSuchAlgorithmException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.bantads.saga.DTO.MensagemDTO;

public class AuthReprovaClienteListener {

    /*@Autowired
    private ModelMapper mapper;

    @Autowired
    private ContaAlteraPerfilProducer prod;*/

    // seq 4
    @RabbitListener(queues = "saga-auth-reprova-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            System.out.println("a conta e o cliente foram reprovados !");
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }
}
