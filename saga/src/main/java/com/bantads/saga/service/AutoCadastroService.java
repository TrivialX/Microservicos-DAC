package com.bantads.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bantads.saga.DTO.AutoCadastroDTO;
import com.bantads.saga.DTO.ClienteDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.sagas.autoCadastro.AuthAutoCadastroProducer;
import com.bantads.saga.sagas.autoCadastro.ClienteAutoCadastroProducer;
import com.bantads.saga.sagas.autoCadastro.ContaAutoCadastroProducer;

@Service
public class AutoCadastroService {

    @Autowired
    private ClienteAutoCadastroProducer clienteprod;

    @Autowired
    private AuthAutoCadastroProducer authprod;

    @Autowired
    private ContaAutoCadastroProducer contaprod;

    public void initSagaAutoCadastro(ClienteDTO dto) {
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        clienteprod.initAutoCadastro(msg);
    }

    public void setContaAutoCadastro(ClienteDTO dto) {
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        contaprod.setContaMessage(msg);
    }


    public void setAuthAutoCadastro (AutoCadastroDTO dto){
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        authprod.setAuthMessage(msg);
    }

    


}
