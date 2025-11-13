package com.sito.sito.security;

import com.sito.sito.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails extends Usuario implements UserDetails {

    public CustomUserDetails(Usuario usuario) {
        // Cambiar setId por setMatricula ya que no tienes campo id
        this.setMatricula(usuario.getMatricula());
        this.setUsuario(usuario.getUsuario());
        this.setContrasena(usuario.getContrasena());
        this.setRol(usuario.getRol());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.getRol().name()));
    }

    @Override
    public String getPassword() {
        return this.getContrasena();
    }

    @Override
    public String getUsername() {
        return this.getUsuario();
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