package com.example.demo.utils;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class Encrypt {

    private static int TAM_SENHA = 8;
    private static int TAM_SALT = 16;

    public static String gerarSenhaSegura(String originalString, String salt) throws NoSuchAlgorithmException {
        String senha = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String senhaComSalt = originalString + salt;
            byte[] encodedhash = digest.digest(senhaComSalt.getBytes(StandardCharsets.UTF_8));

            StringBuilder aux = new StringBuilder();

            for(byte b : encodedhash){
                aux.append(String.format("%02x", 0xFF & b));
            }

            senha = aux.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return senha;
    }


    // Gera uma senha aleatória
    public static String gerarSenhaAleatoria() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder(TAM_SENHA);

        for (int i = 0; i < TAM_SENHA; i++) {
            senha.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }

        return senha.toString();
    }

    public static String gerarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[TAM_SALT];
        random.nextBytes(bytes);
        StringBuilder salt = new StringBuilder();
        for (byte b : bytes) {
            salt.append(String.format("%02x", b));
        }
        return salt.toString();
    }
}
