package com.seplarui.incidencias.modelos;

import jakarta.persistence.*;

@Entity
@Table(name="PRIORIDADES_INCIDENCIA")
public class PrioridadIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRIORIDAD")
    private Long idPrioridad;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @OneToOne(mappedBy = "prioridadIncidencia")
    private Incidencia incidencia;
}
