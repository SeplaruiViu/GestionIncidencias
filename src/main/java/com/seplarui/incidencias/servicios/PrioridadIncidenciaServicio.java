package com.seplarui.incidencias.servicios;

import com.seplarui.incidencias.modelos.PrioridadIncidencia;
import com.seplarui.incidencias.repositorios.PrioridadIncidenciaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PrioridadIncidenciaServicio {

    @Autowired
    PrioridadIncidenciaRepositorio prioridadIncidenciaRepositorio;

    public List<PrioridadIncidencia> findAll() {
        return prioridadIncidenciaRepositorio.findAll();
    }

    public Optional<PrioridadIncidencia> findById(Long idPrioridadIncidencia) {
        return prioridadIncidenciaRepositorio.findById(idPrioridadIncidencia);
    }

    public PrioridadIncidencia save(PrioridadIncidencia prioridadIncidencia) {
        return prioridadIncidenciaRepositorio.save(prioridadIncidencia);
    }

    public void deleteById(long idPrioridadIncidencia) {
        prioridadIncidenciaRepositorio.findById(idPrioridadIncidencia);
    }

    public boolean existsByNombre(String nombre) {
        return prioridadIncidenciaRepositorio.existsByNombre(nombre);
    }
}
