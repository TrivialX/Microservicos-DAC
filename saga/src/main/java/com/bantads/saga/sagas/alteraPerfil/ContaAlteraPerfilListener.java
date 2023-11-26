package com.bantads.saga.sagas.alteraPerfil;

import java.security.NoSuchAlgorithmException;

import com.bantads.saga.DTO.ClienteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import com.bantads.saga.DTO.AutoCadastroDTO;
import com.bantads.saga.DTO.MensagemDTO;
import org.springframework.stereotype.Component;

@Component
public class ContaAlteraPerfilListener {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthAlteraPerfilProducer prod;

    //seq 4
    @RabbitListener(queues = "saga-conta-alteraperfil-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
         try {
            ClienteDTO clienteAPDTO = mapper.map(message.getData(), ClienteDTO.class);
            MensagemDTO msg = new MensagemDTO();
            msg.setMensagem("");
            msg.setData(clienteAPDTO);
            prod.setAuthMessage(msg);
//            prod.setContaMessage(msg);

        } catch (Exception e) {
            System.out.println("erro " + e);
        }


    }
}
