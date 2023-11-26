package com.bantads.saga.sagas.aprovaCliente;

import java.security.NoSuchAlgorithmException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import com.bantads.saga.DTO.MensagemDTO;


public class AuthAprovaListener {

    /*@Autowired
    private ModelMapper mapper;

    @Autowired
    private ContaAlteraPerfilProducer prod;*/

    // seq 4
    @RabbitListener(queues = "saga-auth-aprova-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            System.out.println("aprovado com sucesso !");
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }

}
