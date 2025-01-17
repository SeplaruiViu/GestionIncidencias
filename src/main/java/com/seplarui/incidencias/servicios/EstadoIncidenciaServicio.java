package com.seplarui.incidencias.servicios;

import com.seplarui.incidencias.modelos.EstadoIncidencia;
import com.seplarui.incidencias.repositorios.EstadoIncidenciaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstadoIncidenciaServicio {

    @Autowired
    EstadoIncidenciaRepositorio estadoIncidenciaRepositorio;

    public List<EstadoIncidencia> findAll() {
        return estadoIncidenciaRepositorio.findAll();
    }

    public Optional<EstadoIncidencia> findById(Long idEstadoIncidencia) {
        return estadoIncidenciaRepositorio.findById(idEstadoIncidencia);
    }

    public EstadoIncidencia save (EstadoIncidencia estadoIncidencia) {
        return estadoIncidenciaRepositorio.save(estadoIncidencia);
    }

    public void deleteById(long idEstadoIncidencia) {
        estadoIncidenciaRepositorio.deleteById(idEstadoIncidencia);
    }

    public boolean existsCodEstadoIncidencia(String codEstadoIncidencia) {
      return estadoIncidenciaRepositorio.existsByCodEstado(codEstadoIncidencia);
    }
}
