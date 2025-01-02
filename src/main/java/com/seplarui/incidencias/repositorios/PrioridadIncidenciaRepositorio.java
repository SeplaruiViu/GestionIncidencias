package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.PrioridadIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrioridadIncidenciaRepositorio extends JpaRepository<PrioridadIncidencia, Long> {
    boolean existsByNombre(String nombre);
}
