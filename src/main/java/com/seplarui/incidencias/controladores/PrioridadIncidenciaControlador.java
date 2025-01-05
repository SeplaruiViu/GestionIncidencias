package com.seplarui.incidencias.controladores;


import com.seplarui.incidencias.modelos.Auditoria;
import com.seplarui.incidencias.modelos.PrioridadIncidencia;
import com.seplarui.incidencias.modelos.TipoIncidencia;
import com.seplarui.incidencias.servicios.AuditoriaServicio;
import com.seplarui.incidencias.servicios.PrioridadIncidenciaServicio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prioridadesincidencia")
public class PrioridadIncidenciaControlador {

    @Autowired
    PrioridadIncidenciaServicio prioridadIncidenciaServicio;

    @Autowired
    AuditoriaServicio auditoriaServicio;

    //Listar PrioridadesIncidencia
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<PrioridadIncidencia>> findAll() {
        List<PrioridadIncidencia> listaPrioridadIncidencia = prioridadIncidenciaServicio.findAll();
        auditoriaServicio.guardarAuditoria("listar PrioridadIncidencia", "/prioridadesincidencia/lista");
        return new ResponseEntity<>(listaPrioridadIncidencia , HttpStatus.OK);
    }

    //Detalle PrioridadIncidencia
    @RequestMapping("/detalle/{idPrioridadIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<PrioridadIncidencia>> findById(@PathVariable("idPrioridadIncidencia") long idPrioridadIncidencia) {
        Optional<PrioridadIncidencia> prioridadIncidencia = prioridadIncidenciaServicio.findById(idPrioridadIncidencia);
        auditoriaServicio.guardarAuditoria("detalle PrioridadIncidencia", "/prioridadesincidencia/detalle/" + idPrioridadIncidencia);
        if(prioridadIncidencia.isEmpty() || prioridadIncidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(prioridadIncidencia, HttpStatus.OK);
        }
    }
    
    //Crear PrioridadIncidencia
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createPrioridadIncidencia(@RequestBody PrioridadIncidencia prioridadIncidencia) {
        auditoriaServicio.guardarAuditoria("nuevo PrioridadIncidencia", "/prioridadIncidencia/nuevo");
        if (prioridadIncidencia.getNombre() == null || prioridadIncidencia.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre de la prioridad de incidencia no puede estar en blanco");
        }

        boolean existePrioridadIncidencia = prioridadIncidenciaServicio.existsByNombre(prioridadIncidencia.getNombre());

        if (existePrioridadIncidencia) {
            return ResponseEntity.badRequest().body("El nombre de la prioridad de incidencia ya existe en el sistema");
        }

        PrioridadIncidencia nuevoPrioridadIncidencia = prioridadIncidenciaServicio.save(prioridadIncidencia);
        return new ResponseEntity<>(nuevoPrioridadIncidencia, HttpStatus.CREATED);
    }

    //Actualizar PrioridadIncidencia
    @PutMapping("/actualizar/{idPrioridadIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updatePrioridadIncidencia(@PathVariable("idPrioridadIncidencia") long idPrioridadIncidencia, @RequestBody PrioridadIncidencia prioridadIncidenciaActualizado) {

        //Auditoria
        auditoriaServicio.guardarAuditoria("actualizar PrioridadIncidencia", "/prioridadesincidencia/actualizar/"+idPrioridadIncidencia);

        Optional<PrioridadIncidencia> prioridadIncidenciaOriginal = prioridadIncidenciaServicio.findById(idPrioridadIncidencia);
        if(prioridadIncidenciaOriginal.isEmpty() || prioridadIncidenciaOriginal == null) {
            return ResponseEntity.badRequest().body("No existe la prioridad de incidencia a actualizar");
        }

        //Comprobacion de unicidad de nombre
        //boolean existePrioridadIncidencia = prioridadIncidenciaServicio.existsByNombre(prioridadIncidenciaActualizado.getNombre());
        boolean existePrioridadIncidencia = prioridadIncidenciaServicio.existsByNombreAndIdPrioridadNot(prioridadIncidenciaActualizado.getNombre(), prioridadIncidenciaActualizado.getIdPrioridad());
        if(existePrioridadIncidencia) {
            return ResponseEntity.badRequest().body("El nombre de la prioridad de incidencia ya existe en el sistema");
        }
        if(prioridadIncidenciaActualizado.getNombre() == null || prioridadIncidenciaActualizado.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre de la prioridad de incidencia no puede estar en blanco");
        }

        PrioridadIncidencia prioridadIncidencia = prioridadIncidenciaOriginal.get();

        prioridadIncidencia.setNombre(prioridadIncidenciaActualizado.getNombre());
        prioridadIncidencia.setDescripcion(prioridadIncidenciaActualizado.getDescripcion());

        PrioridadIncidencia prioridadIncidenciaDestino = prioridadIncidenciaServicio.save(prioridadIncidencia);

        return new ResponseEntity<>(prioridadIncidenciaDestino, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idPrioridadIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deletePrioridadIncidencia(@PathVariable("idPrioridadIncidencia") long idPrioridadIncidencia) {

        //Auditoria
        auditoriaServicio.guardarAuditoria("eliminar PrioridadIncidencia", "/prioridadesincidencia/eliminar/"+idPrioridadIncidencia);

        Optional<PrioridadIncidencia> prioridadIncidenciaEliminar = prioridadIncidenciaServicio.findById(idPrioridadIncidencia);
        if(prioridadIncidenciaEliminar.isEmpty() || prioridadIncidenciaEliminar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prioridadIncidenciaServicio.deleteById(idPrioridadIncidencia);
        return new ResponseEntity<>("La prioridad de incidencia con IDENTIFICADOR: " + idPrioridadIncidencia + " se ha eliminado correctamente", HttpStatus.NO_CONTENT);
    }






















}
