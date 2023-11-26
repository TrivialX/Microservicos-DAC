package com.bantads.saga.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bantads.saga.DTO.ClienteDTO;
import com.bantads.saga.DTO.MensagemDTO;
import com.bantads.saga.sagas.alteraPerfil.ClienteAlteraPerfilProducer;

@Service
public class AlteraPerfilService {
    @Autowired
    private ClienteAlteraPerfilProducer prod;

    public void initSagaAlteraPerfil(ClienteDTO dto) {
        MensagemDTO msg = new MensagemDTO();
        msg.setMensagem("");
        msg.setData(dto);
        prod.initAlteraPerfil(msg);
    }
}
