package com.seplarui.incidencias.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="PRIORIDADES_INCIDENCIA")
public class PrioridadIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDPRIORIDAD")
    private Long idPrioridad;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @OneToMany(mappedBy = "prioridadIncidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> listaIncidencias;


}
