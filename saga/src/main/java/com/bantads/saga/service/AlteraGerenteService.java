package com.bantads.saga.service;

import org.springframework.stereotype.Service;

import com.bantads.saga.DTO.GerenteDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.sagas.alteraGerente.GerenteAlteraGerenteProducer;

@Service
public class AlteraGerenteService {

    private GerenteAlteraGerenteProducer prod;

    public void initSagaAlteraGerente(GerenteDTO dto) {
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        prod.initAlteraGerente(msg);
    }
}
