package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByUsuario(String usuario);
    boolean existsByCorreo(String correo);
    Optional<Usuario> findByUsuario(String usuario);
    boolean existsByUsuarioAndIdUsuarioNot(String usuario, Long idUsuario);
    boolean existsByCorreoAndIdUsuarioNot(String correo, Long idUsuario);
}
