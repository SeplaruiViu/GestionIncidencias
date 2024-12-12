package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    boolean existsByNombre(String nombre);
}
