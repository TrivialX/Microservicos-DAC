package com.bantads.saga.sagas.aprovaCliente;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import com.bantads.saga.DTO.SituacaoClienteDTO;
import com.bantads.saga.DTO.ContaDTO;
import com.bantads.saga.DTO.MensagemDTO;
import org.springframework.stereotype.Component;

@Component
public class ContaAprovaListener {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthAprovaProducer prod;

    //seq 2
    @RabbitListener(queues = "saga-conta-aprova-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
         try {
            ContaDTO conta = mapper.map(message.getData(), ContaDTO.class);
            MensagemDTO msg = new MensagemDTO();
            SituacaoClienteDTO situ = new SituacaoClienteDTO();

            situ.setId_cliente(conta.getId_cliente());
            situ.setSituacao("APROVADO");
            msg.setData(situ);

            prod.setAuthMessage(msg);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }


    }
}
