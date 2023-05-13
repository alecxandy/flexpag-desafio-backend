package com.flexpag.paymentscheduler.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

//class detalhes do usuario
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final Optional<User> optionalUser;

    public UserDetails(Optional<User> optionalUser) {
        this.optionalUser = optionalUser;
    }

    @Override //autorização do usuario
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override //senha
    public String getPassword() {
        return optionalUser.orElse(new User()).getPassword();
    }

    @Override //usuario
    public String getUsername() {
        return optionalUser.orElse(new User()).getUserName();
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
