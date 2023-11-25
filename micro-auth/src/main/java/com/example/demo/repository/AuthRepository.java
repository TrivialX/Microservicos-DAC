package com.example.demo.repository;

import com.example.demo.models.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<Auth, String> {

    Auth findByEmailAndSenha(String email, String senha);

    Auth findByEmail(String email);

    Auth findByUsuario(Long id);
}
