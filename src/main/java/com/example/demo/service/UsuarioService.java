package com.example.demo.service;

import com.example.demo.converter.UsuarioConverter;
import com.example.demo.domain.Usuario;
import com.example.demo.dto.UsuarioDTORequest;
import com.example.demo.dto.UsuarioDTOResponse;
import com.example.demo.exceptions.NaoAutorizadoException;
import com.example.demo.exceptions.NaoExisteException;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioConverter converter;
    public void cadastrarNovoUsuario(UsuarioDTORequest usuarioDtoRequest) {
        if (usuarioDtoRequest.getSenha().trim().length() < 8) {
            throw new NaoExisteException("Sua senha deve conter no mínimo 8 caracteres!");
        }
        Optional<Usuario> byNome = repository.findByNome(usuarioDtoRequest.getNome());
        if (byNome.isPresent()) {
            throw new NaoExisteException("Usuário já cadastrado!");
        }
        repository.save(converter.dtoToEntity(usuarioDtoRequest));
    }

    public void validaUsuario(UsuarioDTORequest usuarioDtoRequest) {
        Optional<Usuario> byNome = repository.findByNome(usuarioDtoRequest.getNome());
        if (!byNome.isPresent()) {
            throw new NaoExisteException("Usuário informado não existe!");
        }

    }
}
