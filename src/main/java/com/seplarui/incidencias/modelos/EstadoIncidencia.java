package com.seplarui.incidencias.modelos;

import jakarta.persistence.*;

@Entity
@Table(name="ESTADOS_INCIDENCIA")
public class EstadoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ESTADO_INCIDENCIA")
    private Long idEstadoIncidencia;

    @Column(name="COD_ESTADO")
    private String codEstado;

    @Column(name="DESCRIPCION")
    private String descripcion;

//    @OneToOne(mappedBy = "idestadoincidencia")
//    private Incidencia incidencia;
    @OneToOne(mappedBy = "estadoIncidencia")
    private Incidencia incidencia;

}
