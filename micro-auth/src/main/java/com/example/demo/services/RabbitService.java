package com.example.demo.services;

import com.example.demo.configuration.RabbitConfiguration;
import com.example.demo.models.Auth;
import com.example.demo.repository.AuthRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.utils.Encrypt;

import java.security.NoSuchAlgorithmException;

@Service
public class RabbitService {
    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    AuthRepository authRepo;

    @Autowired
    EmailService emailService;

    public RabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendMessage(Queue queue, String message) {
        rabbitTemplate.convertAndSend(queue.getName(), message );
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
    public void receiveMessageSaga(String message) throws NoSuchAlgorithmException {

        String aux = Encrypt.gerarSenhaAleatoria();

        String salt = Encrypt.gerarSalt();

        String senha = Encrypt.gerarSenhaSegura(aux, salt);

        System.out.println("rato gerado: " + senha);


    }




}
