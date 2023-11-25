package gerentews.com.bantads.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import gerentews.com.bantads.model.IdMensagemDTO;

@Service
public class RabbitService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    GerenteService gerService;

    
    @RabbitListener(queues = "saga-gerente-deletegerente-init")
    public void receiveMessageSaga(@Payload IdMensagemDTO message) throws NoSuchAlgorithmException {
        try {
            Long id = message.getId();
            gerService.deletarGerente(id);
            IdMensagemDTO mensagem = new IdMensagemDTO();
            mensagem.setId(id);
            mensagem.setMessage("gerente deletado do gerenteMS");
            rabbitTemplate.convertAndSend("saga-gerente-deletegerente-end", mensagem);

        } catch (Exception e) {
             IdMensagemDTO mensagem = new IdMensagemDTO();
             mensagem.setErro(true);
             mensagem.setMessage("erro ao deletar gerente: " + e);
             rabbitTemplate.convertAndSend("saga-gerente-deletegerente-end", mensagem);
            System.out.println("erro " + e);
        }

    }
   

}
