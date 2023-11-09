package com.bantads.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bantads.saga.DTO.AuthAutoCadastroDTO;
import com.bantads.saga.DTO.ClienteAutoCadastroDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.sagas.autoCadastro.AuthAutoCadastroProducer;
import com.bantads.saga.sagas.autoCadastro.ClienteAutoCadastroProducer;

@Service
public class AutoCadastroService {

    @Autowired
    private ClienteAutoCadastroProducer clienteprod;
    private AuthAutoCadastroProducer authprod;

    public void initSagaAutoCadastro(ClienteAutoCadastroDTO dto) {
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("1");
        msg.setData(dto);
        clienteprod.initAutoCadastro(msg);
    }

    public void setAuthAutoCadastro (AuthAutoCadastroDTO dto){
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("3");
        msg.setData(dto);
        authprod.setAuthMessage(msg);
    }


}
