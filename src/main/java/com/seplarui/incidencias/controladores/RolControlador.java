package com.seplarui.incidencias.controladores;


import com.seplarui.incidencias.modelos.Rol;
import com.seplarui.incidencias.servicios.AuditoriaServicio;
import com.seplarui.incidencias.servicios.RolServicio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RolControlador {

    @Autowired
    RolServicio rolServicio;

    @Autowired
    AuditoriaServicio auditoriaServicio;

    // Listar Roles
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Rol>> findAll() {
        //Auditoria
        auditoriaServicio.guardarAuditoria("listar Rol", "/roles/lista/");

        List<Rol> listaRoles = rolServicio.findAll();

        return new ResponseEntity<>(listaRoles, HttpStatus.OK);
    }

    //Detalle Rol
    @RequestMapping("/detalle/{idRol}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<Rol>> findById(@PathVariable("idRol") long idRol) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("detalle Rol", "/roles/detalle/"+idRol);

        Optional<Rol> rol = rolServicio.findById(idRol);
        if(rol.isEmpty() || rol == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(rol, HttpStatus.OK);
        }
    }

    // Crear Rol
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createRol(@RequestBody Rol rol)
    {
        //Auditoria
        auditoriaServicio.guardarAuditoria("nuevo Rol", "/roles/nuevo");

        if(rol.getNombre() == null || rol.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del Rol no puede estar en blanco");
        }

        boolean existeRol = rolServicio.existsByNombre(rol.getNombre());
        if(existeRol) {
            return ResponseEntity.badRequest().body("El nombre del Rol ya existe en el sistema.");
        }

        Rol nuevoRol = rolServicio.save(rol);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{idRol}")
    public ResponseEntity<?> updateRol(@PathVariable("idRol") long idRol, @RequestBody Rol rolActualizado) {

        //Auditoria
        auditoriaServicio.guardarAuditoria("actualizar Rol", "/roles/actualizar/"+idRol);


        Optional<Rol> rolOriginal = rolServicio.findById(idRol);
        if(rolOriginal.isEmpty() || rolOriginal == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.badRequest().body("No existe el rol a actualizar");
        }

        //Comprobacion de unicidad de nombre
        boolean existeRol = rolServicio.existsByNombre(rolActualizado.getNombre());
        if(existeRol) {
            return ResponseEntity.badRequest().body("El nombre de rol ya existe en el sistema");
        }
        if(rolActualizado.getNombre() == null || rolActualizado.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del rol no puede estar en blanco");
        }

        Rol rol = rolOriginal.get();

        rol.setNombre(rolActualizado.getNombre());
        rol.setDescripcion(rolActualizado.getDescripcion());
        Rol rolDestino = rolServicio.save(rol);
        return new ResponseEntity<>(rolDestino, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idRol}")
    public ResponseEntity<String>  deleteRol(@PathVariable("idRol") long idRol) {
        //Auditoria
        auditoriaServicio.guardarAuditoria("eliminar Rol", "/roles/eliminar/"+idRol);

        Optional<Rol> rolEliminar = rolServicio.findById(idRol);
        if(rolEliminar.isEmpty() || rolEliminar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        rolServicio.deleteById(idRol);
        return new ResponseEntity<>("El rol con IDENTIFICADOR: " + idRol + "se ha eliminado correctamente", HttpStatus.NO_CONTENT);

    }
}
