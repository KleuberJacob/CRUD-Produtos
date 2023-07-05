package com.example.demo.dto;

import com.example.demo.domain.Enum.AcessoEnum;

public record UsuarioCadastroDTO(String nome, String senha, AcessoEnum acessoEnum) {
}
