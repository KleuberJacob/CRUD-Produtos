package com.example.demo.service;

import com.example.demo.dto.UsuarioDTORequest;
import com.example.demo.dto.UsuarioDTOResponse;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    public UsuarioDTOResponse cadastrarNovoUsuario(UsuarioDTORequest usuarioDtoRequest) {
        return new UsuarioDTOResponse();
    }

    public String validaUsuario(UsuarioDTORequest usuarioDtoRequest) {
        return "null";
    }
}
