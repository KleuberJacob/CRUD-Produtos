package com.example.demo.controller;

import com.example.demo.dto.UsuarioCadastroDTO;
import com.example.demo.dto.UsuarioLoginDTO;
import com.example.demo.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity cadastro(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO) {
        service.cadastrarNovoUsuario(usuarioCadastroDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginDTO usuarioLoginDTO) {
        service.loginUsuario(usuarioLoginDTO);
        return ResponseEntity.ok().build();
    }

}
