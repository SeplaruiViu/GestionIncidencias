package com.seplarui.incidencias.controladores;


import com.seplarui.incidencias.modelos.Departamento;
import com.seplarui.incidencias.servicios.AuditoriaServicio;
import com.seplarui.incidencias.servicios.DepartamentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoControlador {

    @Autowired
    DepartamentoServicio departamentoServicio;

    @Autowired
    AuditoriaServicio auditoriaServicio;

    //Listar Departamentos
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Departamento>> findAll() {

        //Auditoria
        auditoriaServicio.guardarAuditoria("listar Departamento", "/departamentos/lista");


        List<Departamento> listaDepartamentos = departamentoServicio.findAll();

        return new ResponseEntity<>(listaDepartamentos, HttpStatus.OK);
    }

    //Detalle Departamento
    @RequestMapping("/detalle/{idDepartamento}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<Departamento>> findById(@PathVariable("idDepartamento") long idDepartamento) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("detalle Departamento", "/departamentos/detalle/"+idDepartamento);

        Optional<Departamento> departamento = departamentoServicio.findById(idDepartamento);

        if(departamento.isEmpty() || departamento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(departamento, HttpStatus.OK);
        }
    }

    // Crear Departamento
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createDepartamento(@RequestBody Departamento departamento) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("nuevo Departamento", "/departamentos/nuevo");

        if(departamento.getCodDepartamento() == null || departamento.getCodDepartamento().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del Departamento no puede estar en blanco");
        }

        boolean existeDepartamento = departamentoServicio.existsByCodDepartamento(departamento.getCodDepartamento());
        if(existeDepartamento) {
            return ResponseEntity.badRequest().body("El nombre del Departamento ya existe en el sistema.");
        }

        Departamento nuevoDepartamento = departamentoServicio.save(departamento);
        return new ResponseEntity<>(nuevoDepartamento, HttpStatus.CREATED);
    }

    //Actualizar Departamento

    @PutMapping("/actualizar/{idDepartamento}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateDepartamento(@PathVariable("idDepartamento") long idDepartamento, @RequestBody Departamento departamentoActualizado) {

        //Auditoria
        auditoriaServicio.guardarAuditoria("actualizar Departamento", "/departamentos/actualizar/"+idDepartamento);

        Optional<Departamento> departamentoOriginal = departamentoServicio.findById(idDepartamento);
        if(departamentoOriginal.isEmpty() || departamentoOriginal == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.badRequest().body("No existe el Departamento a actualizar");
        }

        //Comprobacion de unicidad de nombre
        boolean existeDepartamento = departamentoServicio.existsByCodDepartamento(departamentoActualizado.getCodDepartamento());
        if(existeDepartamento) {
            return ResponseEntity.badRequest().body("El nombre del Departamento ya existe en el sistema");
        }
        if(departamentoActualizado.getCodDepartamento() == null || departamentoActualizado.getCodDepartamento().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del Departamento no puede estar en blanco");
        }

        Departamento departamento = departamentoOriginal.get();

        departamento.setCodDepartamento(departamentoActualizado.getCodDepartamento());
        departamento.setDescripcion(departamentoActualizado.getDescripcion());
        Departamento departamentoDestino = departamentoServicio.save(departamento);
        return new ResponseEntity<>(departamentoDestino, HttpStatus.OK);
    }

    //Eliminar Departamento
    @DeleteMapping("/eliminar/{idDepartamento}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String>  deleteDepartamento(@PathVariable("idDepartamento") long idDepartamento) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("eliminar Departamento", "/departamentos/eliminar/"+idDepartamento);

        Optional<Departamento> departamentoEliminar = departamentoServicio.findById(idDepartamento);
        if(departamentoEliminar.isEmpty() || departamentoEliminar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        departamentoServicio.deleteById(idDepartamento);
        return new ResponseEntity<>("El Departamento con IDENTIFICADOR: " + idDepartamento + "se ha eliminado correctamente", HttpStatus.NO_CONTENT);

    }


}
