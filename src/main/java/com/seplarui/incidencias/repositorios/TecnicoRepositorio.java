package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepositorio extends JpaRepository<Tecnico, Long> {
}
