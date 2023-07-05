package com.example.demo.domain;

import com.example.demo.domain.Enum.AcessoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", unique = true)
    private String nome;
    @Column(name = "senha")
    private String senha;
    @Enumerated(EnumType.STRING)
    @Column(name = "acesso")
    private AcessoEnum acessoEnum;

    public Usuario(String nome, String senha, AcessoEnum acessoEnum) {
        this.nome = nome;
        this.senha = senha;
        this.acessoEnum = acessoEnum;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.acessoEnum == AcessoEnum.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
