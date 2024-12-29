package com.seplarui.incidencias.servicios;

import com.seplarui.incidencias.modelos.Departamento;
import com.seplarui.incidencias.repositorios.DepartamentoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartamentoServicio {
    @Autowired
    DepartamentoRepositorio departamentoRepositorio;

    public List<Departamento> findAll(){return departamentoRepositorio.findAll();}

    public Optional<Departamento> findById(Long idDepartamento) {
        return departamentoRepositorio.findById(idDepartamento);
    }

    public Departamento save(Departamento departamento) {
        return departamentoRepositorio.save(departamento);
    }

    public void deleteById(long idDepartamento) {
        departamentoRepositorio.deleteById(idDepartamento);
    }

    public boolean existsByCodDepartamento(String codDepartamento) {
        return departamentoRepositorio.existsByCodDepartamento(codDepartamento);
    }
}
