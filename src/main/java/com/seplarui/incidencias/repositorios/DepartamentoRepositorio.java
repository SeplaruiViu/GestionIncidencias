package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {
    boolean existsByCodDepartamento(String codDepartamento);
}
