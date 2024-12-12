package com.seplarui.incidencias.config;

import com.seplarui.incidencias.modelos.Usuario;
import com.seplarui.incidencias.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UsuarioInfoDetalles implements UserDetails {
    private String usuario;
    private String password;
    private GrantedAuthority rol;

    public UsuarioInfoDetalles(Usuario usuario) {
        this.usuario = usuario.getUsuario();
        this.password = usuario.getPassword();
        this.rol = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    { return Collections.singletonList(rol); }
    @Override
    public String getPassword() { return password; }
    @Override
    public String getUsername() { return usuario; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
