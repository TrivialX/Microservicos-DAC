package com.bantads.saga.sagas.alteraPerfil;
import java.security.NoSuchAlgorithmException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

import com.bantads.saga.DTO.ClienteDTO;
import com.bantads.saga.DTO.MensagemDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteAlteraPerfilListener {
     @Autowired
    private ModelMapper mapper;

    @Autowired 
    private ContaAlteraPerfilProducer prod;

    // seq 2
    @RabbitListener(queues = "saga-cliente-alteraperfil-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            ClienteDTO clienteACDTO = mapper.map(message.getData(), ClienteDTO.class);
            MensagemDTO msg = new MensagemDTO();
            msg.setMensagem("");
            msg.setData(clienteACDTO);
            prod.setContaMessage(msg);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }

    }


}
