package cliente.com.bantads.service;


import com.fasterxml.jackson.databind.ObjectMapper;

import cliente.com.bantads.api.ClienteRest;
import cliente.com.bantads.model.AutocadastroDTO;
import cliente.com.bantads.model.Cliente;
import cliente.com.bantads.model.ClienteDTO;
import cliente.com.bantads.model.Message;

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
    private ClienteService clienteService;

    @Autowired
    private ClienteServiceImp clienteServiceImp;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public RabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String queue, Message message) {
        rabbitTemplate.convertAndSend(queue, message);
    }

    @RabbitListener(queues = "teste3")
    public void receiveMessage(String message) throws NoSuchAlgorithmException {
        Cliente a = new Cliente();

        a.setCpf("10599999924");
        a.setEmail("gabriel@gmail.com");
        a.setEndereco("slapoha");
        a.setNome("Gabriel");
        a.setSalario(1000.0);
        a.setTelefone("41999994444");
        System.out.println("rato gerado: " + message);

        Message msg = new Message();
        msg.setData(a);
        this.clienteServiceImp.salvarCliente(a);
    }

    @RabbitListener(queues = "atualiza-cliente")
    public void receiveMessageAtualizaMovimentacao(@Payload Message message){
        try{
            Cliente cli =  mapper.map(message.getData(), Cliente.class);
            cli.setEmail("outrogb@gmail.com");
            this.clienteServiceImp.salvarCliente(cli);
        }catch (Exception ex){
            System.out.println("Deu pau ao enviar ao atualizar o cliente");
        }

    }

    @RabbitListener(queues = "saga-cliente-autocadastro-init")
    public void receiveMessageSaga(@Payload Message message) throws NoSuchAlgorithmException {
        try {
            AutocadastroDTO autocadastro = mapper.map(message.getData(), AutocadastroDTO.class);
            Cliente cliente = mapper.map(autocadastro, Cliente.class);
            this.clienteServiceImp.salvarCliente(cliente);

            autocadastro.setId_cliente(cliente.getId());

            Message msg = new Message();
            msg.setData(autocadastro);

            this.sendMessage("saga-cliente-autocadastro-end", msg);

            msg.setData(cliente);
            this.sendMessage("atualiza-cliente-saga", msg);

        }catch (Exception ex){
            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem(ex.getMessage());
            msg.setErro(true);
            this.sendMessage("saga-cliente-autocadastro-end", msg );
        }

    }

    @RabbitListener(queues = "saga-cliente-alteraperfil-init")
    public void receiveMessageSagaAlteraPerfil(@Payload Message message){
        try{
            ClienteDTO clientedto = mapper.map(message.getData(), ClienteDTO.class);
            Cliente cliente = mapper.map(clientedto, Cliente.class);
            this.clienteServiceImp.salvarCliente(cliente);

            Message msg = new Message();
            msg.setData(clientedto);

            this.sendMessage("saga-cliente-alteraperfil-end", msg);

        }catch (Exception ex){
            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem(ex.getMessage());
            msg.setErro(true);
            this.sendMessage("saga-cliente-alteraperfil-end", msg );
        }

    }

}
