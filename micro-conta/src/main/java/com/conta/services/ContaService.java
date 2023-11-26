package com.conta.services;

import com.conta.DTOS.Message;
import com.conta.models.CUD.ContaCUD;
import com.conta.models.R.ContaR;
import com.conta.models.R.MovimentacoesR;
import com.conta.repository.CUD.ContaRepositoryCUD;
import com.conta.repository.R.ContaRepositoryR;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(String queue, Message message) {
        rabbitTemplate.convertAndSend(queue, message);
    }

    @Autowired
    private ContaRepositoryCUD contaRepoCUD;


    @Transactional("cudTransactionManager")
    public void criarConta(ContaCUD conta){
        try{
            this.contaRepoCUD.save(conta);
            System.out.println("Conta salva");
        }catch (Exception ex){
            System.out.println("Conta deu pau CUD");
        }
    }

    @Transactional("cudTransactionManager")
    public void atualizaConta(ContaCUD conta){
        try{
            this.contaRepoCUD.save(conta);
            Message message = new Message();

            ContaR contar = mapper.map(conta, ContaR.class);
            message.setData(contar);

            sendMessage("atualiza-conta", message);
            System.out.println("ContaCUD atualizada");
        }catch (Exception ex){
            System.out.println("ContaCUD deu pau");
        }
    }

    @Transactional
    public void atualizarIdsDoGerente(List<ContaCUD> contas, Long novoIdGerente) {
        for (ContaCUD conta : contas) {
            conta.setGerente_id(novoIdGerente);
        }
        contaRepoCUD.saveAll(contas);
    }
}
