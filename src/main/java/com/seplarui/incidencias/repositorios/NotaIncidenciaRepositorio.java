package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.NotaIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaIncidenciaRepositorio extends JpaRepository<NotaIncidencia, Long> {

    public List<NotaIncidencia> findByIncidencia_IdIncidencia(Long idIncidencia);

}
