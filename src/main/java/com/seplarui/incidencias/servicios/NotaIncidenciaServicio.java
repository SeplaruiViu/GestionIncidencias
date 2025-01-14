package com.seplarui.incidencias.servicios;

import com.seplarui.incidencias.modelos.NotaIncidencia;
import com.seplarui.incidencias.repositorios.NotaIncidenciaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotaIncidenciaServicio {

    @Autowired
    NotaIncidenciaRepositorio notaIncidenciaRepositorio;

    public List<NotaIncidencia> findAllNotaIncidencia() {
        return notaIncidenciaRepositorio.findAll();
    }

    public NotaIncidencia save(NotaIncidencia notaIncidencia) {
        return notaIncidenciaRepositorio.save(notaIncidencia);
    }

    public Optional<NotaIncidencia> findById(Long idNotaIncidencia) {
        return notaIncidenciaRepositorio.findById(idNotaIncidencia);
    }

    public List<NotaIncidencia> findByIncidencia_IdIncidencia(Long idIncidencia) {
        return notaIncidenciaRepositorio.findByIncidencia_IdIncidencia(idIncidencia);
    }
}
