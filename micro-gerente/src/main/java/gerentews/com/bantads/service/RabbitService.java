package gerentews.com.bantads.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import gerentews.com.bantads.model.Gerente;
import gerentews.com.bantads.model.IdMensagemDTO;
import gerentews.com.bantads.model.MensagemDTO;

@Service
public class RabbitService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    GerenteService gerService;

    @Autowired
    private ModelMapper mapper;
    
    @RabbitListener(queues = "saga-gerente-deletegerente-init")
    public void deleteGerente(@Payload IdMensagemDTO message) throws NoSuchAlgorithmException {
        try {
            Long id = message.getId();
            List<Gerente> gers = gerService.listaGerentes();
            if (gers.size() <= 1){
            IdMensagemDTO mensagem = new IdMensagemDTO();
             mensagem.setErro(true);
             mensagem.setMessage(" impossivel deletar o ultimo gerente");
             rabbitTemplate.convertAndSend("saga-gerente-deletegerente-end", mensagem);
            }else{
            gerService.deletarGerente(id);
            IdMensagemDTO mensagem = new IdMensagemDTO();
            mensagem.setId(id);
            mensagem.setMessage("gerente deletado do gerenteMS");
            rabbitTemplate.convertAndSend("saga-gerente-deletegerente-end", mensagem);
            }
        } catch (Exception e) {
             IdMensagemDTO mensagem = new IdMensagemDTO();
             mensagem.setErro(true);
             mensagem.setMessage(" erro ao deletar gerente: " + e);
             rabbitTemplate.convertAndSend("saga-gerente-deletegerente-end", mensagem);
            System.out.println("erro " + e);
        }

    }
   

    @RabbitListener(queues = "saga-gerente-inseregerente-init")
    public void insereGerente(@Payload MensagemDTO message) throws NoSuchAlgorithmException {
        try {
            Gerente gerente = mapper.map(message.getData(), Gerente.class);
            gerService.salvarGerente(gerente);
            message.setMensagem("gerente inserido no gerenteMS");
            rabbitTemplate.convertAndSend("saga-gerente-inseregerente-end", message);

        } catch (Exception e) {
             IdMensagemDTO mensagem = new IdMensagemDTO();
             mensagem.setErro(true);
             mensagem.setMessage(" erro ao inserir gerente: " + e);
             rabbitTemplate.convertAndSend("saga-gerente-inseregerente-end", mensagem);
            System.out.println("erro " + e);
        }

    }

    @RabbitListener(queues = "saga-gerente-alteragerente-init")
    public void alteraGerente(@Payload MensagemDTO message) throws NoSuchAlgorithmException{
        try {
            Gerente gerente = mapper.map(message.getData(), Gerente.class);
            gerService.salvarGerente(gerente);
            message.setMensagem("gerente alterado com sucesso!");
            rabbitTemplate.convertAndSend("saga-gerente-alteragerente-end", message);

        } catch (Exception e) {
             IdMensagemDTO mensagem = new IdMensagemDTO();
             mensagem.setErro(true);
             mensagem.setMessage("erro ao alterar gerente: " + e);
             rabbitTemplate.convertAndSend("saga-gerente-alteragerente-end", mensagem);
            System.out.println("erro " + e);
        }
    }
}
