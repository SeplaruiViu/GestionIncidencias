package com.seplarui.incidencias.servicios;

import com.seplarui.incidencias.modelos.TipoIncidencia;
import com.seplarui.incidencias.repositorios.TipoIncidenciaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipoIncidenciaServicio {
    @Autowired
    TipoIncidenciaRepositorio tipoIncidenciaRepositorio;

    public List<TipoIncidencia> findAll() {return tipoIncidenciaRepositorio.findAll();}

    public Optional<TipoIncidencia> findById(Long idTipoIncidencia) {
        return tipoIncidenciaRepositorio.findById(idTipoIncidencia);
    }

    public TipoIncidencia save(TipoIncidencia tipoIncidencia){return tipoIncidenciaRepositorio.save(tipoIncidencia);}

    public void deleteById(long idTipoIncidencia) {
        tipoIncidenciaRepositorio.deleteById(idTipoIncidencia);
    }

    public boolean existsByCodTipoIncidencia(String codTipoIncidencia) {
        return tipoIncidenciaRepositorio.existsByCodTipoIncidencia(codTipoIncidencia);
    }
}
