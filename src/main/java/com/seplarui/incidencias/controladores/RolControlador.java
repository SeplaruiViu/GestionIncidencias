package com.seplarui.incidencias.controladores;


import com.seplarui.incidencias.modelos.Rol;
import com.seplarui.incidencias.servicios.RolServicio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RolControlador {

    @Autowired
    RolServicio rolServicio;
    // Listar Roles
    @RequestMapping("/lista")
    public ResponseEntity<List<Rol>> findAll() {
        List<Rol> listaRoles = rolServicio.findAll();

        return new ResponseEntity<>(listaRoles, HttpStatus.OK);
    }

    //Detalle Rol
    @RequestMapping("/detalle/{idRol}")
    public ResponseEntity<Optional<Rol>> findById(@PathVariable("idRol") long idRol) {
        Optional<Rol> rol = rolServicio.findById(idRol);
        if(rol.isEmpty() || rol == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(rol, HttpStatus.OK);
        }
    }

    // Crear Rol
    @PostMapping("/nuevo")
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol)
    {
        Rol nuevoRol = rolServicio.save(rol);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{idRol}")
    public ResponseEntity<Rol> updateRol(@PathVariable("idRol") long idRol, @RequestBody Rol rolActualizado) {
        Optional<Rol> rolOriginal = rolServicio.findById(idRol);
        if(rolOriginal.isEmpty() || rolOriginal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Rol rol = rolOriginal.get();

        rol.setNombre(rolActualizado.getNombre());
        rol.setDescripcion(rolActualizado.getDescripcion());
        Rol rolDestino = rolServicio.save(rol);
        return new ResponseEntity<>(rolDestino, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idRol}")
    public ResponseEntity<String>  deleteRol(@PathVariable("idRol") long idRol) {
        Optional<Rol> rolEliminar = rolServicio.findById(idRol);
        if(rolEliminar.isEmpty() || rolEliminar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        rolServicio.deleteById(idRol);
        return new ResponseEntity<>("El rol con IDENTIFICADOR: " + idRol + "se ha eliminado correctamente", HttpStatus.NO_CONTENT);

    }
}
