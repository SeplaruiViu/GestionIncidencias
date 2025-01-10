package com.seplarui.incidencias.controladores;


import com.seplarui.incidencias.modelos.Departamento;
import com.seplarui.incidencias.modelos.EstadoIncidencia;
import com.seplarui.incidencias.servicios.AuditoriaServicio;
import com.seplarui.incidencias.servicios.EstadoIncidenciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estadosincidencia")
public class EstadoIncidenciaControlador {

    @Autowired
    EstadoIncidenciaServicio estadoIncidenciaServicio;

    @Autowired
    AuditoriaServicio auditoriaServicio;

    //Listar Estados Incidencia
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EstadoIncidencia>> findAll() {

        //Auditoria
        auditoriaServicio.guardarAuditoria("listar EstadoIncidencia", "/estadosincidencia/lista");

        List<EstadoIncidencia> listaEstadosIncidencia = estadoIncidenciaServicio.findAll();

        return new ResponseEntity<>(listaEstadosIncidencia, HttpStatus.OK);
    }

    @RequestMapping("/detalle/{idEstadoIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<EstadoIncidencia>> findById(@PathVariable("idEstadoIncidencia") long idEstadoIncidencia) {

        //Auditoria
        auditoriaServicio.guardarAuditoria("detalle EstadoIncidencia", "/estadosincidencia/detalle/"+ idEstadoIncidencia);

        Optional<EstadoIncidencia> estadoIncidencia = estadoIncidenciaServicio.findById(idEstadoIncidencia);

        if(estadoIncidencia.isEmpty() || estadoIncidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(estadoIncidencia, HttpStatus.OK);
        }
    }


    // Crear Estado Incidencia
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createEstadoIncidencia(@RequestBody EstadoIncidencia estadoIncidencia) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("nuevo EstadoIncidencia", "/estadosincidencia/nuevo");

        if(estadoIncidencia.getCodEstado() == null || estadoIncidencia.getCodEstado().isEmpty()) {
            return ResponseEntity.badRequest().body("El código del Estado Incidencia no puede estar en blanco");
        }

        boolean existeEstadoIncidencia = estadoIncidenciaServicio.existsCodEstadoIncidencia(estadoIncidencia.getCodEstado());
        if(existeEstadoIncidencia) {
            return ResponseEntity.badRequest().body("El código del Estado Incidencia ya existe en el sistema.");
        }

        EstadoIncidencia nuevoEstadoIncidencia = estadoIncidenciaServicio.save(estadoIncidencia);
        return new ResponseEntity<>(nuevoEstadoIncidencia, HttpStatus.CREATED);
    }

    //Actualizar Estado Incidencia

    @PutMapping("/actualizar/{idEstadoIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateEstadoIncidencia(@PathVariable("idEstadoIncidencia") long idEstadoIncidencia, @RequestBody EstadoIncidencia estadoIncidenciaActualizado) {

        //Auditoria
        auditoriaServicio.guardarAuditoria("actualizar EstadoIncidencia", "/estadosincidencia/actualizar/"+idEstadoIncidencia);

        Optional<EstadoIncidencia> estadoIncidenciaOriginal = estadoIncidenciaServicio.findById(idEstadoIncidencia);
        if(estadoIncidenciaOriginal.isEmpty() || estadoIncidenciaOriginal == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.badRequest().body("No existe el Estado Incidencia a actualizar");
        }

        //Comprobacion de unicidad de nombre
        boolean existeEstadoIncidencia = estadoIncidenciaServicio.existsCodEstadoIncidencia(estadoIncidenciaActualizado.getCodEstado());
        if(existeEstadoIncidencia) {
            return ResponseEntity.badRequest().body("El nombre del Estado Incidencia ya existe en el sistema");
        }
        if(estadoIncidenciaActualizado.getCodEstado() == null || estadoIncidenciaActualizado.getCodEstado().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del Estado Incidencia no puede estar en blanco");
        }

        EstadoIncidencia estadoIncidencia = estadoIncidenciaOriginal.get();

        estadoIncidencia.setCodEstado(estadoIncidenciaActualizado.getCodEstado());
        estadoIncidencia.setDescripcion(estadoIncidenciaActualizado.getDescripcion());
        EstadoIncidencia estadoIncidenciaDestino = estadoIncidenciaServicio.save(estadoIncidencia);
        return new ResponseEntity<>(estadoIncidenciaDestino, HttpStatus.OK);
    }


    //Eliminar Estado Incidencia
    @DeleteMapping("/eliminar/{idEstadoIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String>  deleteEstadoIncidencia(@PathVariable("idEstadoIncidencia") long idEstadoIncidencia) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("eliminar Estado Incidencia", "/estadosincidencia/eliminar/"+idEstadoIncidencia);

        Optional<EstadoIncidencia> estadoIncidenciaEliminar = estadoIncidenciaServicio.findById(idEstadoIncidencia);
        if(estadoIncidenciaEliminar.isEmpty() || estadoIncidenciaEliminar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        estadoIncidenciaServicio.deleteById(idEstadoIncidencia);
        return new ResponseEntity<>("El Estado Incidencia con IDENTIFICADOR: " + idEstadoIncidencia + "se ha eliminado correctamente", HttpStatus.NO_CONTENT);

    }
}
