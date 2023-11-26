package com.example.demo.controllers;

import com.example.demo.DTOS.AuthDTO;
import com.example.demo.models.Auth;
import com.example.demo.DTOS.LoginDTO;
import com.example.demo.services.AuthService;
import com.example.demo.services.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    EmailService emailService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("")
    ResponseEntity<AuthDTO> logar(@RequestBody LoginDTO loginDTORequest) throws NoSuchAlgorithmException {
        Auth user = authService.autenticar(loginDTORequest.getEmail(), loginDTORequest.getSenha());
        AuthDTO Auth = modelMapper.map(user, AuthDTO.class);

        if(user != null){
            return ResponseEntity.ok().body(Auth);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("")
    String  sendEmail(){
        emailService.enviarEmail("vinie.antuness@gmail.com", "teste", "testando");
        return "Email enviado com sucesso";
    }

    @GetMapping("/teste")
    void teste(){
        Auth user = authService.buscaUser(4L);
        user.setTipoUser("TESTE");
        user.setUsuario(4L);
        user.setSalt("asdasda");
        user.setNome("teste");
        user.setEmail("teste@test.com");
        user.setSenha("asdasda1");

        this.authService.save(user);
;    }

}
