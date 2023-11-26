package com.conta.services;


import com.conta.DTOS.AutocadastroDTO;
import com.conta.DTOS.ContaSagaDTO;
import com.conta.DTOS.Message;
import com.conta.models.CUD.ContaCUD;
import com.conta.models.R.ContaR;
import com.conta.models.R.MovimentacoesR;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
public class RabbitService {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaServiceR contaServiceR;

    @Autowired
    private MovimentacaoServiceR movimentacaoServiceR;

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
        a.setId_gerente(1L);
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

    @RabbitListener(queues = "atualiza-movimentacao")
    public void receiveMessageAtualizaMovimentacao(@Payload Message message){
        try{
            MovimentacoesR movi =  mapper.map(message.getData(), MovimentacoesR.class);
            movi.setDatahora(LocalDateTime.now());
            this.movimentacaoServiceR.atualizaMovimentacao(movi);
        }catch (Exception ex){
            System.out.println("Deu pau ao enviar novo deposito atualizacao");
        }

    }

    @RabbitListener(queues = "atualiza-conta")
    public void receiveMessageAtualizaConta(@Payload Message message){
        try{
            ContaR conta = mapper.map(message.getData(), ContaR.class);
            this.contaServiceR.atualizarConta(conta);

        }catch (Exception ex){
            System.out.println("Deu ruim att conta R");
        }
    }

    @RabbitListener(queues = "atualiza-conta-saga")
    public void receiveMessageAtualizaContaSaga(@Payload Message message){
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
            Long id_gerente = this.contaServiceR.buscaGerentesContas();
            ContaCUD conta = mapper.map(autocadastro, ContaCUD.class);
            conta.setGerenteId(id_gerente);
            this.contaService.criarConta(conta);

            Message msg = new Message();
            msg.setData(autocadastro);

            this.sendMessage("saga-conta-autocadastro-end", msg);

            msg.setData(conta);
            this.sendMessage("atualiza-conta-saga", msg);

        }catch (Exception ex){
            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem(ex.getMessage());
            msg.setErro(true);
            this.sendMessage("saga-conta-autocadastro-end", msg );
        }

    }

}
