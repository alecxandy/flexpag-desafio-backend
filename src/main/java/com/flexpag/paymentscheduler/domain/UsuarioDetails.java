package com.flexpag.paymentscheduler.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

//class detalhes do usuario
public class UsuarioDetails implements UserDetails {

    private final Optional<Usuario> optionalUsuario;

    public UsuarioDetails(Optional<Usuario> optionalUsuario) {
        this.optionalUsuario = optionalUsuario;
    }

    @Override //autorização do usuario
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override //senha
    public String getPassword() {
        return optionalUsuario.orElse(new Usuario()).getSenha();
    }

    @Override //usuario
    public String getUsername() {
        return optionalUsuario.orElse(new Usuario()).getNome();
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
