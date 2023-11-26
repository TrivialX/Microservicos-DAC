package com.bantads.saga.sagas.insereGerente;

import java.security.NoSuchAlgorithmException;

//import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.MensagemDTO;
//import com.bantads.saga.service.InsereGerenteService;
//import com.bantads.saga.DTO.AuthAutoCadastroDTO;

@Component
public class AuthInsereGerenteReceiver {

    /*@Autowired
    private ModelMapper mapper;

    @Autowired
    private InsereGerenteService aService;*/
    //seq 6
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
