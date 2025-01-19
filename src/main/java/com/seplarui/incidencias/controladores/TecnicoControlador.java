package com.seplarui.incidencias.controladores;

import com.seplarui.incidencias.modelos.Rol;
import com.seplarui.incidencias.modelos.Tecnico;
import com.seplarui.incidencias.servicios.AuditoriaServicio;
import com.seplarui.incidencias.servicios.TecnicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoControlador {

    @Autowired
    TecnicoServicio tecnicoServicio;

    @Autowired
    AuditoriaServicio auditoriaServicio;

    //Listar Tecnicos
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Tecnico>> findAll() {
        //Auditoria
        auditoriaServicio.guardarAuditoria("listar Técnico", "/tecnicos/lista");

        List<Tecnico> listaTecnicos = tecnicoServicio.findAll();
//        System.out.println(listaTecnicos.get(0).getDepartamento());
        return new ResponseEntity<>(listaTecnicos, HttpStatus.OK);
    }

    //Detalle Tecnico
    @RequestMapping("/detalle/{idTecnico}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<Tecnico>> findById(@PathVariable("idTecnico") long idTecnico) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("detalle Técnico", "/tecnicos/detalle/"+idTecnico);

        Optional<Tecnico> tecnico = tecnicoServicio.findById(idTecnico);
        if(tecnico.isEmpty() || tecnico == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tecnico, HttpStatus.OK);
        }
    }

    //Crear Tecnico
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createTecnico(@RequestBody Tecnico tecnico)
    {
        //Auditoria
        auditoriaServicio.guardarAuditoria("nuevo Técnico", "/tecnicos/nuevo");

        Tecnico nuevoTecnico = tecnicoServicio.save(tecnico);
        return new ResponseEntity<>(nuevoTecnico, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{idTecnico}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateTecnico(@PathVariable("idTecnico") long idTecnico, @RequestBody Tecnico tecnicoActualizado) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("actualizar Técnico", "/tecnicos/actualizar/"+idTecnico);

        Optional<Tecnico> tecnicoOriginal = tecnicoServicio.findById(idTecnico);
        if(tecnicoOriginal.isEmpty() || tecnicoOriginal == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.badRequest().body("No existe el técnico a actualizar");
        }

        //Comprobacion de unicidad de nombre
//        boolean existeRol = rolServicio.existsByNombre(tecnicoActualizado.getNombre());
//        if(existeRol) {
//            return ResponseEntity.badRequest().body("El nombre de rol ya existe en el sistema");
//        }
        if(tecnicoActualizado.getNombre() == null || tecnicoActualizado.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del técnico no puede estar en blanco");
        }

        Tecnico tecnico = tecnicoOriginal.get();

        tecnico.setNombre(tecnicoActualizado.getNombre());
        tecnico.setApellidos(tecnicoActualizado.getApellidos());
        tecnico.setDepartamento(tecnicoActualizado.getDepartamento());
        Tecnico tecnicoDestino = tecnicoServicio.save(tecnico);
        return new ResponseEntity<>(tecnicoDestino, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idTecnico}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteTecnico(@PathVariable("idTecnico") long idTecnico) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("eliminar Técnico", "/tecnicos/eliminar/"+idTecnico);

        Optional<Tecnico> tecnicoEliminar = tecnicoServicio.findById(idTecnico);
        if(tecnicoEliminar == null || tecnicoEliminar.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tecnicoServicio.deleteById(idTecnico);
        return new ResponseEntity<>("El tecnico con IDENTIFICADOR: " + idTecnico + " se ha eliminado correctamente", HttpStatus.OK);
    }
}
