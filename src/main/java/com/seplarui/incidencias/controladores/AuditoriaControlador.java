package com.seplarui.incidencias.controladores;

import com.seplarui.incidencias.modelos.Auditoria;
import com.seplarui.incidencias.servicios.AuditoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaControlador {

    @Autowired
    AuditoriaServicio auditoriaServicio;

    //Listar Auditoria
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Auditoria>> findAll() {
        List<Auditoria> listaAuditoria = auditoriaServicio.findAll();
        return new ResponseEntity<>(listaAuditoria, HttpStatus.OK);
    }
}
