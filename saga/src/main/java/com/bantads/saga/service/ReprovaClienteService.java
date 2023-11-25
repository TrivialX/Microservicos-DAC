package com.bantads.saga.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.saga.DTO.ContaDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.sagas.reprovaCliente.ContaReprovaClienteProducer;

public class ReprovaClienteService {
      @Autowired
    private ContaReprovaClienteProducer prod;

    public void initSagaReprovaCliente(ContaDTO dto) {
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        prod.setContaMessage(msg);
    }
    
}
