package com.seplarui.incidencias.servicios;

import com.seplarui.incidencias.modelos.Incidencia;
import com.seplarui.incidencias.repositorios.IncidenciaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class IncidenciaServicio {

    @Autowired
    IncidenciaRepositorio incidenciaRepositorio;

    //Listar todas las incidencias de todos los usuarios

    public List<Incidencia> findAll() {
        return incidenciaRepositorio.findAll();
    }

    public Incidencia save(Incidencia incidencia) {
        return incidenciaRepositorio.save(incidencia);
    }

    public void deleteById(long idIncidencia) {
        incidenciaRepositorio.deleteById(idIncidencia);
    }


}
