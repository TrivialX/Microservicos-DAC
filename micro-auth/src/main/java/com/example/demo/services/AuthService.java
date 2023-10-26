package com.example.demo.services;

import com.example.demo.models.Auth;
import com.example.demo.repository.AuthRepository;
import com.example.demo.utils.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AuthService {

    @Autowired
    AuthRepository repo;


    public void save(String nome, String email, String senha, String salt, String tipUser, Long idUser){
        this.repo.save(new Auth(nome,email, senha,salt, tipUser, idUser));
    }

    public Auth autenticar(String email, String senha) throws NoSuchAlgorithmException {
        Auth conta = this.repo.findByEmail(email);
        System.out.println("Email: " + email + ", senha: " + senha);
        if(conta != null){
            String aux = Encrypt.gerarSenhaSegura(senha, conta.getSalt());
            System.out.println("Senha encriptada: " + aux );
            if(conta.getSenha().equals(aux)){
                //deu boa
                System.out.println("Conta: " + conta );
                return conta;
            }

        }

        return null;
    }
}
