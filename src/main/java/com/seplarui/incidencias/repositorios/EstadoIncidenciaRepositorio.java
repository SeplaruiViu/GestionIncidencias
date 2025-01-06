package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.EstadoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoIncidenciaRepositorio extends JpaRepository<EstadoIncidencia, Long> {

    boolean existsByCodEstadoIncidencia(String codEstadoIncidencia);

}
