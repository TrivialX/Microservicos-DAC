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


    public void save(Auth auth){
        this.repo.save(auth);
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

    public Auth buscaUser(Long id){
        return this.repo.findByUsuario(id);

    }

    public void deletaUserById(String id){
        this.repo.deleteById(id);

    }
}
