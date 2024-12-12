package com.seplarui.incidencias.servicios;


import com.seplarui.incidencias.modelos.Rol;
import com.seplarui.incidencias.modelos.Usuario;
import com.seplarui.incidencias.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> findById(Long idUsuario) {
        return usuarioRepositorio.findById(idUsuario);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public void deleteById(long idUsuario) {
        usuarioRepositorio.deleteById(idUsuario);
    }

    public boolean existsByUsuario(String usuario) {
        return usuarioRepositorio.existsByUsuario(usuario);
    }

    public boolean existsByCorreo(String correo) {
        return usuarioRepositorio.existsByCorreo(correo);
    }

    public boolean existsByUsuarioAndIdUsuarioNot(String usuario, Long idUsuario) { return usuarioRepositorio.existsByUsuarioAndIdUsuarioNot(usuario, idUsuario);}

    public boolean existsByCorreoAndIdUsuarioNot(String correo, Long idUsuario) {return usuarioRepositorio.existsByCorreoAndIdUsuarioNot(correo, idUsuario);}
}
