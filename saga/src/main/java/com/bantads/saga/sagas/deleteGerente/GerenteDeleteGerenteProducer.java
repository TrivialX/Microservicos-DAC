package com.bantads.saga.sagas.deleteGerente;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.saga.DTO.IdMensagemDTO;


@Component
public class GerenteDeleteGerenteProducer {
    @Autowired
    private AmqpTemplate template;
    
    //Seq 1
    public void initDeleteGerente(IdMensagemDTO dto) {
        template.convertAndSend(ConfigDeleteGerente.queueDeleteGerente().getName(), dto);
    }

}
