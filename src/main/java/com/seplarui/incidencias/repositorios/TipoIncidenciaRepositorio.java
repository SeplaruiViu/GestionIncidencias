package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.TipoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIncidenciaRepositorio extends JpaRepository<TipoIncidencia, Long> {
    boolean existsByCodTipoIncidencia(String codTipoIncidencia);
}
