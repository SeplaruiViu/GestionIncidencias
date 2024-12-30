package com.seplarui.incidencias.servicios;

import com.seplarui.incidencias.modelos.Tecnico;
import com.seplarui.incidencias.repositorios.TecnicoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TecnicoServicio {

    @Autowired
    TecnicoRepositorio tecnicoRepositorio;

    public List<Tecnico> findAll() {
        return tecnicoRepositorio.findAll();
    }

    public Optional<Tecnico> findById(Long idTecnico) {
        return tecnicoRepositorio.findById(idTecnico);
    }

    public Tecnico save(Tecnico tecnico) {
        return tecnicoRepositorio.save(tecnico);
    }

    public void deleteById(long idTecnico) {
        tecnicoRepositorio.deleteById(idTecnico);
    }
}
