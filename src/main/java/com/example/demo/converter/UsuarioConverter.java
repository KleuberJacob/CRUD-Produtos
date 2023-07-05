package com.example.demo.converter;

import com.example.demo.domain.Usuario;
import com.example.demo.dto.UsuarioDTORequest;
import com.example.demo.dto.UsuarioDTOResponse;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {

    public UsuarioDTOResponse entityToDto(Usuario usuario) {
        UsuarioDTOResponse dto = new UsuarioDTOResponse();
        dto.setNome(usuario.getNome());
        return dto;
    }

    public Usuario dtoToEntity(UsuarioDTORequest usuarioDtoRequest) {
        Usuario entity = new Usuario();
        entity.setNome(usuarioDtoRequest.getNome());
        entity.setSenha(usuarioDtoRequest.getSenha());
        return entity;
    }

}
