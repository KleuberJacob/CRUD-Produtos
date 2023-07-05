package com.example.demo.controller;

import com.example.demo.dto.UsuarioDTORequest;
import com.example.demo.dto.UsuarioDTOResponse;
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
    public UsuarioDTOResponse cadastroDeUsuario(@Valid @RequestBody UsuarioDTORequest usuarioDtoRequest) {
        UsuarioDTOResponse usuarioDtoResponse = service.cadastrarNovoUsuario(usuarioDtoRequest);
        return usuarioDtoResponse;
    }

    @PostMapping("/validar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> validaUsuario(@Valid @RequestBody UsuarioDTORequest usuarioDtoRequest) {
        String s = service.validaUsuario(usuarioDtoRequest);
        return ResponseEntity.ok(s);
    }

}
