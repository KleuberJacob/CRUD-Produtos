package com.example.demo.service;

import com.example.demo.domain.Usuario;
import com.example.demo.dto.UsuarioCadastroDTO;
import com.example.demo.dto.UsuarioLoginDTO;
import com.example.demo.exceptions.NaoExisteException;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
//    private final UsuarioLoginConverter usuarioLoginConverter;
//    private final UsuarioCadastroConverter usuarioCadastroConverter;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void cadastrarNovoUsuario(UsuarioCadastroDTO usuarioCadastroDTO) {
        if(repository.findByNome(usuarioCadastroDTO.nome()) != null) {
            throw new NaoExisteException("Usuário informado já existe!");
        }
        if (usuarioCadastroDTO.senha().trim().length() < 8) {
            throw new NaoExisteException("Sua senha deve conter no mínimo 8 caracteres!");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDTO.senha());
        Usuario newUsuario = new Usuario(usuarioCadastroDTO.nome(), senhaCriptografada, usuarioCadastroDTO.acessoEnum());

        repository.save(newUsuario);
    }

    public String loginUsuario(UsuarioLoginDTO usuarioLoginDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.nome(), usuarioLoginDTO.senha());
        var auth = authenticationManager.authenticate(userNamePassword);
        

//        if(repository.findByNome(usuarioLoginDTO.nome()) == null) {
//            throw new NaoExisteException("Usuário informado não existe!");
//        }
//        UserDetails byNome = repository.findByNome(usuarioLoginDTO.nome());
//        if (repository.findByNome(usuarioLoginDTO.nome() == null) {
//            throw new NaoExisteException("Usuário informado não existe!");
//        }
        return "teste";

    }

}
