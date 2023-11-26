package com.conta.services;


import com.conta.DTOS.AutocadastroDTO;
import com.conta.DTOS.ClienteDTO;
import com.conta.DTOS.ContaSagaDTO;
import com.conta.DTOS.IdMensagemDTO;
import com.conta.DTOS.Message;
import com.conta.DTOS.MessageListDTO;
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
import java.util.List;
import java.util.stream.Collectors;

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

    public void sendMessageID(String queue, IdMensagemDTO message) {
        rabbitTemplate.convertAndSend(queue, message);
    }


     public void sendMessageList(String queue, MessageListDTO message) {
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
//            Long id_gerente = this.contaServiceR.buscaGerentesContas();
            ContaCUD conta = mapper.map(autocadastro, ContaCUD.class);
//            conta.setGerenteId(id_gerente);
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

    @RabbitListener(queues = "saga-conta-deletegerente-init")
    public void receiveMessageDeleteGerenteSaga(@Payload IdMensagemDTO message) throws NoSuchAlgorithmException {
        try {
            Long id_gerente = message.getId();
            List <ContaR> contas = contaServiceR.buscaContasGerente(id_gerente);
            List<ContaCUD> contasCUD = contas.stream().map(conta -> mapper.map(conta, ContaCUD.class)).collect(Collectors.toList());
            Long gerMenosCont = this.contaServiceR.buscaGerentesContas();
            contaService.atualizarIdsDoGerente(contasCUD, gerMenosCont);
            MessageListDTO messlist = new MessageListDTO();
            messlist.setData(contasCUD);
            this.sendMessageID("saga-conta-deletegerente-end", message);

            for (ContaCUD conta: messlist.getData()) {
                Message msg = new Message();
                msg.setData(conta);
                this.sendMessage("atualiza-conta-saga", msg);
            }


        }catch (Exception ex){
            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem(ex.getMessage());
            msg.setErro(true);
            this.sendMessage("saga-conta-deletegerente-end", msg );
        }

    }



//    saga-conta-alteraperfil-init
    @RabbitListener(queues = "saga-conta-alteraperfil-init")
    public void receiveMessageSagaAlteraPerfil(@Payload Message message) throws NoSuchAlgorithmException {
        try{
            ClienteDTO clieteDTO = mapper.map(message.getData(), ClienteDTO.class);

            ContaR contaR = this.contaServiceR.buscaContaPorIdCliente(clieteDTO.getId());

            System.out.println("GERENTE CONTAR: " +contaR.getGerenteId());
            ContaCUD contaCUD = mapper.map(contaR,ContaCUD.class);
            System.out.println("GERENTE contaCUD: " +contaCUD.getGerenteId());

            Double limite = clieteDTO.getLimite();

            contaCUD.setLimite(limite);
            contaCUD.setSaldo(clieteDTO.getSaldo());
            contaR.setLimite(limite);
            contaR.setSaldo(clieteDTO.getSaldo());

            this.contaService.atualizaConta(contaCUD);
            Message msg = new Message();

            msg.setData(clieteDTO);
            this.sendMessage("saga-conta-alteraperfil-end", msg);

        }catch (Exception ex){
            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem(ex.getMessage());
            msg.setErro(true);
            this.sendMessage("saga-conta-alteraperfil-end", msg );
        }
    }
}
