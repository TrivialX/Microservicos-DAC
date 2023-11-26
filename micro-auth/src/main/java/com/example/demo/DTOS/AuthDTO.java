package com.example.demo.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {

    private String email;
    private String senha;
    private String nome;
    private Long usuario;
    private String tipoUser;

}
