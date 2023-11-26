package com.bantads.saga.sagas.reprovaCliente;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

import com.bantads.saga.DTO.ContaDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.DTO.SituacaoClienteDTO;
import com.bantads.saga.sagas.aprovaCliente.AuthAprovaProducer;

public class ContaReprovaClienteListener {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthAprovaProducer prod;

    //seq 2
    @RabbitListener(queues = "saga-conta-reprova-end")
    public void receiveMessageSaga(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
         try {
            ContaDTO conta = mapper.map(message.getData(), ContaDTO.class);
            SituacaoClienteDTO msg = new SituacaoClienteDTO();
            msg.setId_cliente(conta.getId_cliente());
            msg.setSituacao("REPROVADO");
            prod.setAuthMessage(msg);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }
    }
   
}
