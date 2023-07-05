package com.example.demo.controller;

import com.example.demo.dto.UsuarioDTORequest;
import com.example.demo.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String cadastroDeUsuario(@RequestBody @Valid UsuarioDTORequest usuarioDtoRequest) {
        service.cadastrarNovoUsuario(usuarioDtoRequest);
        return "Cadastrado com sucesso!";
    }

    @PostMapping("/validar")
    public void validaUsuario(@RequestBody @Valid UsuarioDTORequest usuarioDtoRequest) {
        service.validaUsuario(usuarioDtoRequest);
    }

}
