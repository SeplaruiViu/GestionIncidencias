package com.seplarui.incidencias.controladores;


import com.seplarui.incidencias.modelos.Usuario;
import com.seplarui.incidencias.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioControlador(UsuarioServicio usuarioServicio, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioServicio = usuarioServicio;
    }
    @GetMapping("/sinpass")
    public String goHome() {
        return "Estoy en el controlador de usuarios.";
    }
    // Lista usuarios
    @RequestMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> listaUsuarios = usuarioServicio.findAll();

        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
    }

    //Detalle usuario
    @RequestMapping("/detalle/{idUsuario}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public ResponseEntity<?> findById(@PathVariable("idUsuario") long idUsuario) {
        Optional<Usuario> usuario = usuarioServicio.findById(idUsuario);
        if(usuario.isEmpty() || usuario == null) {
            return ResponseEntity.badRequest().body("No existe el usuario");
        } else {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
    }

    //Crear usuario
    @PostMapping("/nuevo")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {

        if(usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del usuario no puede estar en blanco");
        }
        if(usuario.getApellidos() == null || usuario.getApellidos().isEmpty()) {
            return ResponseEntity.badRequest().body("Los apellidos del usuario no puede estar en blanco");
        }

        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("El password no puede estar en blanco");
        }

        boolean existeUsuario = usuarioServicio.existsByUsuario(usuario.getUsuario());

        if(existeUsuario) {
            return ResponseEntity.badRequest().body("El usuario ya existe en el sistema");
        }

        boolean existeCorreo = usuarioServicio.existsByCorreo(usuario.getCorreo());
        if(existeCorreo) {
            return ResponseEntity.badRequest().body("El correo ya existe en el sistema");
        }


        String password = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(password);
        Usuario nuevoUsuario = usuarioServicio.save(usuario);


        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    //Modificar usuario
    @PutMapping("/actualizar/{idUsuario}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateUsuario(@PathVariable("idUsuario") long idUsuario, @RequestBody Usuario usuarioActualizado) {
        Optional<Usuario> usuarioOriginal = usuarioServicio.findById(idUsuario);
        if(usuarioOriginal.isEmpty() || usuarioOriginal == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.badRequest().body("No existe el usuario a actualizar");
        }

        if(usuarioActualizado.getUsuario() == null || usuarioActualizado.getUsuario().isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario no puede estar en blanco");
        }

        if(usuarioActualizado.getCorreo() == null || usuarioActualizado.getCorreo().isEmpty()) {
            return ResponseEntity.badRequest().body("El correo del usuario no puede estar en blanco");
        }

        if(usuarioActualizado.getPassword() == null || usuarioActualizado.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("El password no puede estar en blanco");
        }

        if(usuarioActualizado.getNombre() == null || usuarioActualizado.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del usuario no puede estar en blanco");
        }

        if(usuarioActualizado.getApellidos() == null || usuarioActualizado.getApellidos().isEmpty()) {
            return ResponseEntity.badRequest().body("Los apellidos del usuario no puede estar en blanco");
        }

//        boolean existeUsuario = usuarioServicio.existsByUsuario(usuarioActualizado.getUsuario());

//        if(existeUsuario) {
//            return ResponseEntity.badRequest().body("El usuario ya existe en el sistema");
//        }
//
//        boolean existeCorreo = usuarioServicio.existsByCorreo(usuarioActualizado.getCorreo());
//        if(existeCorreo) {
//            return ResponseEntity.badRequest().body("El correo ya existe en el sistema");
//        }

        boolean existeUsuario = usuarioServicio.existsByUsuarioAndIdUsuarioNot(usuarioActualizado.getUsuario(), usuarioOriginal.get().getIdUsuario());

        if(existeUsuario) {
            return ResponseEntity.badRequest().body("El usuario ya existe en el sistema");
        }

        boolean existeCorreo = usuarioServicio.existsByCorreoAndIdUsuarioNot(usuarioActualizado.getCorreo(), usuarioOriginal.get().getIdUsuario());
        if(existeCorreo) {
            return ResponseEntity.badRequest().body("El correo ya existe en el sistema");
        }

        Usuario usuario = usuarioOriginal.get();

        usuario.setUsuario(usuarioActualizado.getUsuario());
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setApellidos(usuarioActualizado.getApellidos());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        if(usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }
        usuario.setRol(usuarioActualizado.getRol());
        Usuario usuarioDestino = usuarioServicio.save(usuario);

        return new ResponseEntity<>(usuarioDestino, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("idUsuario") long idUsuario) {
        Optional<Usuario> usuarioEliminar = usuarioServicio.findById(idUsuario);

        if(usuarioEliminar == null || usuarioEliminar.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioServicio.deleteById(idUsuario);
        return new ResponseEntity<>("El usuario con IDENTIFICADOR: " + idUsuario + " se ha eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}
