package com.bantads.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bantads.saga.DTO.AuthAutoCadastroDTO;
import com.bantads.saga.DTO.GerenteDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.sagas.insereGerente.AuthInsereGerenteProducer;
import com.bantads.saga.sagas.insereGerente.ContaInsereGerenteProducer;
import com.bantads.saga.sagas.insereGerente.GerenteInsereGerenteProducer;

@Service
public class InsereGerenteService {

    @Autowired
    private GerenteInsereGerenteProducer prod;

    @Autowired
    private ContaInsereGerenteProducer cprod;
    
    @Autowired
    private AuthInsereGerenteProducer authprod;

    public void initSagaInsereGerente(GerenteDTO dto) {
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        prod.initInsereGerente(msg);
    }

    public void setContaIGMessage(GerenteDTO dto) {
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        cprod.setContaInsereGerenteMessage(msg);
    }

    public void setAuthIGMessage(AuthAutoCadastroDTO dto){
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        authprod.setAuthInsereGerenteMessage(msg);
    }

    


    
}
