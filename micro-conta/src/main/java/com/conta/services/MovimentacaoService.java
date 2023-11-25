package com.conta.services;

import com.conta.DTOS.DepositoDTO;
import com.conta.DTOS.Message;
import com.conta.models.R.ContaR;
import com.conta.repository.CUD.MovimentacoesRepositoryCUD;
import com.conta.repository.R.MovimentacoesRepositoryR;
import com.conta.models.R.MovimentacoesR;
import com.conta.models.CUD.MovimentacoesCUD;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {


    @Autowired
    private MovimentacoesRepositoryCUD movimentacaoRepoCUD;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(String queue, Message message) {
        rabbitTemplate.convertAndSend(queue, message);
    }


    @Autowired
    private ModelMapper mapper;

    @Autowired
    ContaServiceR servideContaR;
    @Transactional("cudTransactionManager")
    public void deposito(MovimentacoesCUD deposito){
        try{

            this.movimentacaoRepoCUD.save(deposito);

            System.out.println("Deposito salvo");

            Message message = new Message();
            message.setData(deposito);

            sendMessage("atualiza-movimentacao", message);
            sendMessage("atv_8", message);

        }catch (Exception ex){
            System.out.println("Conta deu pau Deposito" + ex);
        }

    }

    @Transactional("cudTransactionManager")
    public void saque(MovimentacoesCUD saque){
        try{
            this.movimentacaoRepoCUD.save(saque);
            System.out.println("Saque salvo");

            Message message = new Message();

            MovimentacoesR moviR = mapper.map(saque, MovimentacoesR.class);
            message.setData(moviR);

            sendMessage("atualiza-movimentacao", message);
        }catch (Exception ex){
            System.out.println("Conta deu pau Saque");
        }
    }


    @Transactional("cudTransactionManager")
    public void transferencia(MovimentacoesCUD transferencia){
        try{
            this.movimentacaoRepoCUD.save(transferencia);
            System.out.println("Transferencia registrada conta_origem");

            Message message = new Message();

            MovimentacoesR moviR = mapper.map(transferencia, MovimentacoesR.class);
            message.setData(moviR);
            sendMessage("atualiza-movimentacao", message);
        }catch (Exception ex){
            System.out.println("Conta deu pau Transferencia conta_origem");
        }

    }


}
