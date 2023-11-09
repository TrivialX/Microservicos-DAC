package com.conta.services;


import com.conta.DTOS.AutocadastroDTO;
import com.conta.DTOS.ContaSagaDTO;
import com.conta.DTOS.Message;
import com.conta.models.CUD.ContaCUD;
import com.conta.models.R.ContaR;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class RabbitService {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaServiceR contaServiceR;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public RabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String queue, Message message) {
        rabbitTemplate.convertAndSend(queue, message);
    }

    @RabbitListener(queues = "teste")
    public void receiveMessage(String message) throws NoSuchAlgorithmException {
        AutocadastroDTO a = new AutocadastroDTO();
        a.setSaldo(10.0);
        a.setLimite(10.000);
        a.setGerenteId(1L);
        a.setId_cliente(1L);
        a.setSituacao("Pendente");
        System.out.println("rato gerado: " + message);

        Message msg = new Message();
        msg.setData(a);
        this.sendMessage("saga-conta-autocadastro-init", msg);
    }

    @RabbitListener(queues = "teste2")
    public void receiveMessageAtualizaConta2(String message) {

        ContaR conta = new ContaR();

        conta.setLimite(10.0);
        conta.setGerenteId(1L);
        conta.setId_cliente(1L);

        this.contaServiceR.atualizarConta(conta);

    }

    @RabbitListener(queues = "atualiza-conta")
    public void receiveMessageAtualizaConta(@Payload Message message){
        try{
            ContaR conta = mapper.map(message.getData(), ContaR.class);
            System.out.println(conta.toString());
            this.contaServiceR.atualizarConta(conta);

        }catch (Exception ex){
            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem(ex.getMessage());
            msg.setErro(true);
            this.sendMessage("saga-conta-autocadastro-end", msg );
        }
    }

    @RabbitListener(queues = "saga-conta-autocadastro-init")
    public void receiveMessageSaga(@Payload Message message) throws NoSuchAlgorithmException {
        try {
            AutocadastroDTO autocadastro = mapper.map(message.getData(), AutocadastroDTO.class);
            ContaCUD conta = mapper.map(autocadastro, ContaCUD.class);
            this.contaService.criarConta(conta);

            Message msg = new Message();
            msg.setData(autocadastro);

            this.sendMessage("saga-conta-autocadastro-end", msg);

            msg.setData(conta);
            this.sendMessage("atualiza-conta", msg);

        }catch (Exception ex){
            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem(ex.getMessage());
            msg.setErro(true);
            this.sendMessage("saga-conta-autocadastro-end", msg );
        }

    }

}
