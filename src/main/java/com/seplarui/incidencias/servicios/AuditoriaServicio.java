package com.seplarui.incidencias.servicios;

import com.seplarui.incidencias.modelos.Auditoria;
import com.seplarui.incidencias.repositorios.AuditoriaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class AuditoriaServicio {

    @Autowired
    AuditoriaRepositorio auditoriaRepositorio;

    public Auditoria save(Auditoria auditoria) {


        return auditoriaRepositorio.save(auditoria);
    }


    public void guardarAuditoria(String accion, String endPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();

        Auditoria auditoria = new Auditoria();
        auditoria.setAccion(accion);
        auditoria.setEndPoint(endPoint);
        auditoria.setUsuario(usuario);
        auditoria.setFechaAccion(LocalDateTime.now());
        auditoriaRepositorio.save(auditoria);
    }
}
