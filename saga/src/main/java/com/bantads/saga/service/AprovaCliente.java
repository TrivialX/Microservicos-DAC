package com.bantads.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bantads.saga.DTO.ContaDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.sagas.aprovaCliente.ContaAprovaProducer;

@Service
public class AprovaCliente {
    @Autowired
    private ContaAprovaProducer prod;

    public void initSagaAprovaCliente(ContaDTO dto) {
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        prod.setContaMessage(msg);
    }
}

