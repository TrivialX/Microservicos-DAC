package com.example.demo.services;


import com.example.demo.DTOS.AprovaClienteDTO;
import com.example.demo.DTOS.AutocadastroDTO;
import com.example.demo.DTOS.Message;
import com.example.demo.models.Auth;
import com.example.demo.repository.AuthRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.utils.Encrypt;

import java.security.NoSuchAlgorithmException;

@Service
public class RabbitService {
    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    AuthRepository authRepo;

    @Autowired
    AuthService authService;


    @Autowired
    EmailService emailService;

    public RabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendMessage(String queue, Message message) {
        rabbitTemplate.convertAndSend(queue, message);
    }


//    @RabbitListener(queues = "auth-cria-user")
//    public void receiveMessageMicros(String mensagem) {
//        try {
//            Auth auth = objectMapper.readValue(mensagem, Auth.class);
//
//            authRepo.save(auth);
//            String emailMessage;
//            String emailTitle;
//            String emailDestination = auth.getEmail();
//
//            if(auth.getEmail().equals("CLIENTE")){
//                emailTitle = "Bem-Vindo ao BanTads";
//                emailMessage = "Olá " + auth.getNome() + " Sua conta foi aprovada no BanTads.\n\n" +
//                        "Segue sua senha para entrar na plataforma: " + auth.getSenha();
//
//            }else{
//                emailTitle = "Acesso liberado";
//                emailMessage = "Olá, " + auth.getNome() + ", segue senha para acesso a plataforma do Bantads\n\n" +
//                        "Senha:" + auth.getSenha();
//            }
//            emailService.enviarEmail(emailDestination, emailTitle, emailMessage );
//
//        }catch (Exception e) {
//            System.err.println("Erro ao desserializar a mensagem JSON: " + e.getMessage());
//        }
//
//    }

//    @RabbitListener(queues = "auth-reprova-user")
//    public void receiveMessageMicross(String mensagem) {
//        try {
//            JsonNode jsonNode = objectMapper.readTree(mensagem);
//            String motivo = jsonNode.get("motivo").asText();
//
//            Auth auth = objectMapper.readValue(mensagem, Auth.class);
//
//            authRepo.delete(auth);
//
//            String emailTitle = auth.getNome() + ", sua conta foi recusada";
//            String msg = "Olá, " + auth.getNome() + ", sua conta for recusado pelos seguintes motivos. \n\n\n" +
//                    motivo;
//
//            emailService.enviarEmail(auth.getEmail(), emailTitle, msg);
//
//        }catch (Exception e) {
//            System.err.println("Erro ao desserializar a mensagem JSON: " + e.getMessage());
//        }
//    }


//    @RabbitListener(queues = "auth-alterar-user")
//    public void receiveMessageAlterarUser(String mensagem){
//        try {
//            Auth auth = objectMapper.readValue(mensagem, Auth.class);
//
//            String emailTitle = "Alteração de conta BanTads";
//            String msg = "Olá, " + auth.getNome() + ", sua conta for alterada com sucesso.";
//
//            emailService.enviarEmail(auth.getEmail(), emailTitle, msg);
//
//        }catch (Exception e) {
//            System.err.println("Erro ao desserializar a mensagem JSON: " + e.getMessage());
//        }
//
//    }

    @RabbitListener(queues = "teste")
    public void receiveMessageTeste(String message) throws NoSuchAlgorithmException {

        String aux = Encrypt.gerarSenhaAleatoria();

        String salt = Encrypt.gerarSalt();

        String senha = Encrypt.gerarSenhaSegura(aux, salt);

        System.out.println("rato gerado: " + senha);

    }

   private void SendEmailWelcome(String email, String nome, String senha, String typeUser){
        String emailTitle;
        String emailMessage;

        if(typeUser.equals("CLIENTE")){
            emailTitle = "Bem-Vindo ao BanTads";
            emailMessage = "Olá " + nome + " Sua conta foi aprovada no BanTads.\n\n" +
                    "Segue sua senha para entrar na plataforma: " + senha;

        }else{
            emailTitle = "Acesso liberado";
            emailMessage = "Olá, " + nome + ", segue senha para acesso a plataforma do Bantads\n\n" +
                    "Senha:" + senha;
        }

        emailService.enviarEmail(email, emailTitle, emailMessage);
   }

   private void SendEmailWelcomeCliente(String email, String nome){
       String emailTitle;
       String emailMessage;
       emailTitle = "Bem-Vindo ao BanTads";
       emailMessage = "Olá " + nome + ", Seja bme vindo ao nogocinho";
       emailService.enviarEmail(email, emailTitle, emailMessage);
   }


    @RabbitListener(queues = "saga-auth-autocadastro-init")
    public void receiveMessageSaga(@Payload Message message){
        try{
            AutocadastroDTO base = mapper.map(message.getData(), AutocadastroDTO.class);
            Auth auth = mapper.map(base, Auth.class);

            auth.setTipoUser("CLIENTE");

            auth.setUsuario(base.getId_cliente());

            try {
                this.authRepo.save(auth);
                System.out.println("Auth salvo");

            }catch (Exception ex){
                System.out.println("Auth deu ruim");
                Message msg = new Message();
                msg.setData(null);
                msg.setMensagem(ex.getMessage());
                msg.setErro(true);
                this.sendMessage("saga-auth-autocadastro-end", msg);
                return;
            }

            this.SendEmailWelcomeCliente(auth.getEmail(), auth.getNome());
            base.setSenha(auth.getSenha());

            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem("OK");

            this.sendMessage("saga-auth-autocadastro-end", msg);

        }catch (Exception ex){
            System.out.println("Auth deu ruim");
            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem(ex.getMessage());
            msg.setErro(true);
            this.sendMessage("saga-auth-autocadastro-end", msg );
        }
    }

    @RabbitListener(queues = "saga-auth-aprova-init")
    public void receiveMessageSagaAprova(@Payload Message message){
        try {
            AprovaClienteDTO base = mapper.map(message.getData(), AprovaClienteDTO.class);
            Long id = base.getId_cliente();

            Auth auth = this.authService.buscaUser(id);

            if(auth == null) {
                Message msg = new Message();
                msg.setMensagem("SEM CLIENTE PARA ESSE ID: " + id);
                msg.setErro(true);
                this.sendMessage("saga-auth-aprova-end", msg);
                return;
            }

            String senha_base = Encrypt.gerarSenhaAleatoria();
            String salt = Encrypt.gerarSalt();
            String senha = Encrypt.gerarSenhaSegura(senha_base, salt);
            auth.setSalt(salt);
            auth.setSenha(senha);

            this.authService.save(auth);

            this.SendEmailWelcome(auth.getEmail(), auth.getNome(), senha_base, auth.getTipoUser());

            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem("OK");
            this.sendMessage("saga-auth-aprova-end", msg);

        }catch (Exception ex){
            System.out.println("Aprova deu ruim");
            Message msg = new Message();
            msg.setData(null);
            msg.setMensagem(ex.getMessage());
            msg.setErro(true);
            this.sendMessage("saga-auth-aprova-end", msg );
        }

    }







}
