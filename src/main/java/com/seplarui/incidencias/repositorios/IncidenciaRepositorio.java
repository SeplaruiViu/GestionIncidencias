package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

}
