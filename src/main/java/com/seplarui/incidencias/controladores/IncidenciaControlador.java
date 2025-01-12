package com.seplarui.incidencias.controladores;

import com.seplarui.incidencias.modelos.Incidencia;
import com.seplarui.incidencias.servicios.AuditoriaServicio;
import com.seplarui.incidencias.servicios.IncidenciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaControlador {

    @Autowired
    IncidenciaServicio incidenciaServicio;

    @Autowired
    AuditoriaServicio auditoriaServicio;

    //Listar todos las incidencias de todos los usuarios
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Incidencia>> findAll() {
        //Auditoria
        auditoriaServicio.guardarAuditoria("listar Incidencia", "/incidencias/lista");

        List<Incidencia> listaIncidencias = incidenciaServicio.findAll();

        return new ResponseEntity<>(listaIncidencias, HttpStatus.OK);
    }
}
