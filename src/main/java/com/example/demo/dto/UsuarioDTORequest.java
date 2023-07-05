package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTORequest {

    @NotNull(message = "O nome deve ser informado.")
    private String nome;
    @NotNull(message = "A senha deve ser informada.")
    private String senha;

}
