package com.seplarui.incidencias.controladores;


import com.seplarui.incidencias.modelos.TipoIncidencia;
import com.seplarui.incidencias.servicios.TipoIncidenciaServicio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tiposincidencia")
public class TipoIncidenciaControlador {

    @Autowired
    TipoIncidenciaServicio tipoIncidenciaServicio;

    // Listar TiposIncidencia
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<TipoIncidencia>> findAll() {
        List<TipoIncidencia> listaTipoIncidencia = tipoIncidenciaServicio.findAll();
        return new ResponseEntity<>(listaTipoIncidencia, HttpStatus.OK);
    }

    //Detalle TipoIncidencia
    @RequestMapping("/detalle/{idTipoIncidencia}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<TipoIncidencia>> findById(@PathVariable("idTipoIncidencia") long idTipoIncidencia) {
        Optional<TipoIncidencia> tipoIncidencia = tipoIncidenciaServicio.findById(idTipoIncidencia);
        if (tipoIncidencia.isEmpty() || tipoIncidencia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tipoIncidencia, HttpStatus.OK);
        }
    }

    //Crear TipoIncidencia
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createTipoIncidencia(@RequestBody TipoIncidencia tipoIncidencia) {
        if (tipoIncidencia.getCodTipoIncidencia() == null || tipoIncidencia.getCodTipoIncidencia().isEmpty()) {
            return ResponseEntity.badRequest().body("El código del tipo de incidencia no puede estar en blanco");
        }

        boolean existeTipoIncidencia = tipoIncidenciaServicio.existsByCodTipoIncidencia(tipoIncidencia.getCodTipoIncidencia());
        if (existeTipoIncidencia) {
            return ResponseEntity.badRequest().body("El código del tipo de incidencia ya existe en el sistema");
        }

        TipoIncidencia nuevoTipoIncidencia = tipoIncidenciaServicio.save(tipoIncidencia);
        return new ResponseEntity<>(nuevoTipoIncidencia, HttpStatus.CREATED);
    }
    @PutMapping("/actualizar/{idTipoIncidencia}")
    public ResponseEntity<?> updateTipoIncidencia(@PathVariable("idTipoIncidencia") long idTipoIncidencia, @RequestBody TipoIncidencia tipoIncidenciaActualizado) {
        Optional<TipoIncidencia> tipoIncidenciaOriginal = tipoIncidenciaServicio.findById(idTipoIncidencia);
        if(tipoIncidenciaOriginal.isEmpty() || tipoIncidenciaOriginal == null) {
            return ResponseEntity.badRequest().body("No existe el tipo de incidencia a actualizar");
        }

        //Comprobacion de unicidad de codigo
        boolean existeTipoIncidencia = tipoIncidenciaServicio.existsByCodTipoIncidencia(tipoIncidenciaActualizado.getCodTipoIncidencia());

        if(existeTipoIncidencia) {
            return ResponseEntity.badRequest().body("El código del tipo de incidencia ya existe en el sistema");
        }
        if(tipoIncidenciaActualizado.getCodTipoIncidencia() == null || tipoIncidenciaActualizado.getCodTipoIncidencia().isEmpty()) {
            return ResponseEntity.badRequest().body("El código del tipo de incidencia no puede estar en blanco");
        }

        TipoIncidencia tipoIncidencia = tipoIncidenciaOriginal.get();

        tipoIncidencia.setCodTipoIncidencia(tipoIncidenciaActualizado.getCodTipoIncidencia());
        tipoIncidencia.setDescripcion(tipoIncidenciaActualizado.getDescripcion());

        TipoIncidencia tipoIncidenciaDestino = tipoIncidenciaServicio.save(tipoIncidencia);

        return new ResponseEntity<>(tipoIncidenciaDestino, HttpStatus.OK);
    }


    @DeleteMapping("/eliminar/{idTipoIncidencia}")
    public ResponseEntity<String> deleteTipoIncidencia(@PathVariable("idTipoIncidencia") long idTipoIncidencia) {
        Optional<TipoIncidencia> tipoIncidenciaEliminar = tipoIncidenciaServicio.findById(idTipoIncidencia);
        if(tipoIncidenciaEliminar.isEmpty() || tipoIncidenciaEliminar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tipoIncidenciaServicio.deleteById(idTipoIncidencia);
        return new ResponseEntity<>("El tipo de incidencia con IDENTIFICADOR : " +idTipoIncidencia + "se ha eliminado correctamente", HttpStatus.NO_CONTENT);
    }


























}