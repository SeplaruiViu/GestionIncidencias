package com.seplarui.incidencias.modelos;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="INCIDENCIAS")
public class Incidencia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDINCIDENCIA")
    private Long idIncidencia;

    @Column(name="TITULO")
    private String titulo;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="FECHACREACION")
    private Date fechaCreacion;


    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "incidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaIncidencia> listaNotas;

    @ManyToOne
    @JoinColumn(name = "IDTECNICO", nullable = false)
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "IDESTADOINCIDENCIA")
    private EstadoIncidencia estadoIncidencia;

    @ManyToOne
    @JoinColumn(name="IDPRIORIDADINCIDENCIA")
    private PrioridadIncidencia prioridadIncidencia;

    @ManyToOne
    @JoinColumn(name = "IDTIPOINCIDENCIA")
    private TipoIncidencia tipoIncidencia;


}
