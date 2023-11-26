package com.bantads.saga.sagas.alteraPerfil;

import java.security.NoSuchAlgorithmException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.bantads.saga.DTO.MensagemDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthAlteraPerfilListener {
    
    
    /*
     * @Autowired
     * private ModelMapper mapper;
     * 
     * @Autowired
     * private AutoCadastroService aService;
     */

    // seq 6
    @RabbitListener(queues = "saga-auth-alteraperfil-end")
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
