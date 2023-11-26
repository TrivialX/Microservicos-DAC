package com.bantads.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bantads.saga.DTO.IdMensagemDTO;
import com.bantads.saga.sagas.deleteGerente.AuthDeleteGerenteProducer;
import com.bantads.saga.sagas.deleteGerente.ContaDeleteGerenteProducer;
import com.bantads.saga.sagas.deleteGerente.GerenteDeleteGerenteProducer;

@Service
public class DeleteGerenteService {
    
    @Autowired
    private GerenteDeleteGerenteProducer prod;

    @Autowired
    private ContaDeleteGerenteProducer cprod;
    
    @Autowired
    private AuthDeleteGerenteProducer authprod;

    public void initDeleteGerente(long id) {
        IdMensagemDTO msg = new IdMensagemDTO();
        msg.setMessage("");
        msg.setId(id);
        prod.initDeleteGerente(msg);
    }

    public void setContaDGMessage(long id) {
      IdMensagemDTO msg = new IdMensagemDTO();
        msg.setMessage("");
        msg.setId(id);
        cprod.setContaDeleteGerenteMessage(msg);
    }

    public void setAuthDGMessage(long id){
        IdMensagemDTO msg = new IdMensagemDTO();
        msg.setMessage("");
        msg.setId(id);
        authprod.setAuthDeleteGerenteMessage(msg);
    }

}
