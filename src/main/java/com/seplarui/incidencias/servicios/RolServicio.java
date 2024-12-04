package com.seplarui.incidencias.servicios;

import com.seplarui.incidencias.modelos.Rol;
import com.seplarui.incidencias.repositorios.RolRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolServicio {
    @Autowired
    RolRepositorio rolRepositorio;


    public List<Rol> findAll() {
        return rolRepositorio.findAll();
    }

    public Optional<Rol> findById(Long idRol) {
        return rolRepositorio.findById(idRol);
    }

    public Rol save(Rol rol) {
        return rolRepositorio.save(rol);
    }

    public void deleteById(long idRol) {
        rolRepositorio.deleteById(idRol);
    }

    public boolean existsByNombre(String nombre) {return rolRepositorio.existsByNombre(nombre);}
}
