package com.seplarui.incidencias.controladores;

import com.seplarui.incidencias.modelos.Auditoria;
import com.seplarui.incidencias.servicios.AuditoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuditoriaServicio auditoriaServicio;

    @PostMapping("/login")
  //  @CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde cualquier origen
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsuario(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario autenticado correctamente");
        response.put("username", loginRequest.getUsuario());
        response.put("password", loginRequest.getPassword());
        response.put("status","ok");

        //Auditoria
        auditoriaServicio.guardarAuditoria("Login", "/api/login");

        return ResponseEntity.ok(response);
    }
}
