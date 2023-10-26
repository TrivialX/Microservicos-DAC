package com.example.demo.controllers;

import com.example.demo.models.Auth;
import com.example.demo.DTOS.LoginDTO;
import com.example.demo.services.AuthService;
import com.example.demo.services.EmailService;
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


    @PostMapping("")
    ResponseEntity<Auth> logar(@RequestBody LoginDTO loginDTORequest) throws NoSuchAlgorithmException {
        Auth user = authService.autenticar(loginDTORequest.getEmail(), loginDTORequest.getSenha());
        if(user != null){
            return ResponseEntity.ok().body(user);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("")
    String  sendEmail(){
        emailService.enviarEmail("vinie.antuness@gmail.com", "teste", "testando");
        return "Email enviado com sucesso";
    }

}
