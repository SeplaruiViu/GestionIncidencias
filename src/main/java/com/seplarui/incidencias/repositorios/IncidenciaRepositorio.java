package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

    List<Incidencia> findByUsuario_IdUsuario(Long idUsuario);

    List<Incidencia> findByUsuarioUsuario(String usuario);
//    List<Incidencia> findByTecnicoId(Long tecnicoId);
//    List<Incidencia> findByTecnicoUsuarioUsuario(String usuarioTecnico);

}
