package com.seplarui.incidencias.config;

import com.seplarui.incidencias.modelos.Usuario;
import com.seplarui.incidencias.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UsuarioInfoDetallesServicio implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Usuario> usuario = usuarioRepositorio.findByUsuario(username);
        return usuario.map(UsuarioInfoDetalles::new).orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado el usuario"));
    }
}
