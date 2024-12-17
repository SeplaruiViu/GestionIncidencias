package com.seplarui.incidencias.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="ESTADOS_INCIDENCIA")
public class EstadoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDESTADOINCIDENCIA")
    private Long idEstadoIncidencia;

    @Column(name = "CODESTADOINCIDENCIA")
    private String codEstado;

    @Column(name= "DESCRIPCION")
    private String descripcion;

    @OneToMany(mappedBy = "estadoIncidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> listaIncidencias;



}
