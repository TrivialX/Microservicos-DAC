package com.example.demo.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlteraClienteDTO {
    private Long id;
    private String nome;
    private String email;
}
