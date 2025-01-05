package com.seplarui.incidencias.repositorios;

import com.seplarui.incidencias.modelos.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriaRepositorio extends JpaRepository<Auditoria, Long> {
}
