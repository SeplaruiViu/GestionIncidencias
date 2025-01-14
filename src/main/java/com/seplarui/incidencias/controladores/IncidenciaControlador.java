package com.seplarui.incidencias.controladores;

import com.seplarui.incidencias.modelos.Incidencia;
import com.seplarui.incidencias.modelos.NotaIncidencia;
import com.seplarui.incidencias.servicios.AuditoriaServicio;
import com.seplarui.incidencias.servicios.IncidenciaServicio;
import com.seplarui.incidencias.servicios.NotaIncidenciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaControlador {

    @Autowired
    IncidenciaServicio incidenciaServicio;

    @Autowired
    AuditoriaServicio auditoriaServicio;

    @Autowired
    NotaIncidenciaServicio notaIncidenciaServicio;

    //Listar todos las incidencias de todos los usuarios
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Incidencia>> findAll() {
        //Auditoria
        auditoriaServicio.guardarAuditoria("listar Incidencia", "/incidencias/lista");

        List<Incidencia> listaIncidencias = incidenciaServicio.findAll();

        return new ResponseEntity<>(listaIncidencias, HttpStatus.OK);
    }

    //Detalle Incidencia
    @RequestMapping("/detalle/{idIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<Incidencia>> findById(@PathVariable("idIncidencia") long idIncidencia) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("detalle Incidencia", "/incidencias/detalle/"+idIncidencia);

        Optional<Incidencia> incidencia = incidenciaServicio.findById(idIncidencia);

        if(incidencia.isEmpty() || incidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(incidencia, HttpStatus.OK);
        }
    }

    //Crear Incidencia
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createIncidencia(@RequestBody Incidencia incidencia) {
        System.out.println("JSON recibido: " + incidencia);
        //Auditoria
        auditoriaServicio.guardarAuditoria("nuevo Incidencia", "/incidencias/nuevo");

        if(incidencia.getEstadoIncidencia() == null) {
            return ResponseEntity.badRequest().body("El estado de la incidencia no puede estar en blanco");
        }

        if(incidencia.getPrioridadIncidencia() == null) {
            return ResponseEntity.badRequest().body("La prioridad de la incidencia no puede estar en blanco");
        }

        if(incidencia.getTipoIncidencia() == null) {
            return ResponseEntity.badRequest().body("El tipo de la incidencia no puede estar en blanco");
        }

        if(incidencia.getDescripcion() == null) {
            return ResponseEntity.badRequest().body("La descripción de la incidencia no puede estar en blanco");
        }

        if(incidencia.getTitulo() == null) {
            return ResponseEntity.badRequest().body("El título de la incidencia no puede estar en blanco");
        }

        if(incidencia.getTecnico() == null) {
            return ResponseEntity.badRequest().body("El técnico de la incidencia no puede estar en blanco");
        }


        Incidencia nuevoIncidencia = incidenciaServicio.save(incidencia);
        return new ResponseEntity<>(nuevoIncidencia, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{idIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateIncidencia(@PathVariable("idIncidencia") long idIncidencia, @RequestBody Incidencia incidenciaActualizado) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("actualizar Incidencia", "/incidencias/actualizar/"+idIncidencia);

        Optional<Incidencia> incidenciaOriginal = incidenciaServicio.findById(idIncidencia);
        if(incidenciaOriginal.isEmpty() || incidenciaOriginal == null) {
            return ResponseEntity.badRequest().body("No existe la incidencia a actualizar");
        }

        if(incidenciaActualizado.getEstadoIncidencia() == null) {
            return ResponseEntity.badRequest().body("El estado de la incidencia no puede estar en blanco");
        }

        if(incidenciaActualizado.getPrioridadIncidencia() == null) {
            return ResponseEntity.badRequest().body("La prioridad de la incidencia no puede estar en blanco");
        }

        if(incidenciaActualizado.getTipoIncidencia() == null) {
            return ResponseEntity.badRequest().body("El tipo de la incidencia no puede estar en blanco");
        }

        if(incidenciaActualizado.getDescripcion() == null) {
            return ResponseEntity.badRequest().body("La descripción de la incidencia no puede estar en blanco");
        }

        if(incidenciaActualizado.getTitulo() == null) {
            return ResponseEntity.badRequest().body("El título de la incidencia no puede estar en blanco");
        }

        if(incidenciaActualizado.getTecnico() == null) {
            return ResponseEntity.badRequest().body("El técnico de la incidencia no puede estar en blanco");
        }

        Incidencia incidencia = incidenciaOriginal.get();

        incidencia.setTipoIncidencia(incidenciaActualizado.getTipoIncidencia());
        incidencia.setDescripcion(incidenciaActualizado.getDescripcion());
        incidencia.setTitulo(incidenciaActualizado.getTitulo());
        incidencia.setEstadoIncidencia(incidenciaActualizado.getEstadoIncidencia());
        incidencia.setPrioridadIncidencia(incidenciaActualizado.getPrioridadIncidencia());
        incidencia.setTecnico(incidenciaActualizado.getTecnico());

        Incidencia incidenciaDestino = incidenciaServicio.save(incidencia);

        return new ResponseEntity<>(incidenciaDestino, HttpStatus.OK);

    }

    @PostMapping("/anyadirnota/{idIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> anyadirNota(@PathVariable Long idIncidencia, @RequestBody NotaIncidencia notaIncidencia) {
        Incidencia incidenciaExistente = incidenciaServicio.findByIdNoOptional(idIncidencia);
        if(incidenciaExistente == null) {
            return ResponseEntity.badRequest().body("No se encuentra la incidencia seleccionada");
        }

        notaIncidencia.setIncidencia(incidenciaExistente);

        notaIncidenciaServicio.save(notaIncidencia);

        return new ResponseEntity<>(notaIncidencia, HttpStatus.CREATED);

    }

    @GetMapping("/listanota/{idIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<List<NotaIncidencia>> findAllNotasIncidencia(@PathVariable Long idIncidencia) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("listar Nota Incidencia", "/incidencias/listanota/" + idIncidencia);

//        List<NotaIncidencia> listaNotasIncidencia = notaIncidenciaServicio.findAllNotaIncidencia();
        List<NotaIncidencia> listaNotasIncidencia = notaIncidenciaServicio.findByIncidencia_IdIncidencia(idIncidencia);

        return new ResponseEntity<>(listaNotasIncidencia, HttpStatus.OK);
    }
}
