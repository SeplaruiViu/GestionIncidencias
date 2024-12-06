package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByUsuario(String usuario);
    boolean existsByCorreo(String correo);
}
