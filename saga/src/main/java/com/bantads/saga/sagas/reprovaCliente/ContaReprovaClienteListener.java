package com.bantads.saga.sagas.reprovaCliente;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.ContaDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.DTO.SituacaoClienteDTO;
import com.bantads.saga.sagas.aprovaCliente.AuthAprovaProducer;

@Component
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
            SituacaoClienteDTO situ = new SituacaoClienteDTO();
            MensagemDTO msg = new MensagemDTO();

            situ.setId_cliente(conta.getId_cliente());
            situ.setSituacao("REPROVADO");
            situ.setObservacao(conta.getObservacao());
            prod.setAuthMessage(msg);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }
    }
   
}
