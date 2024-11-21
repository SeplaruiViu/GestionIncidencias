package com.seplarui.incidencias.modelos;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="INCIDENCIAS")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INCIDENCIA")
    private Long idIncidencia;

    @Column(name="TITULO")
    private String titulo;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="FECHA_CREACION")
    private Date fechaCreacion;

//    @Column(name="ID_ESTADO")
//    private int idEstado;

    @Column(name="ID_USUARIO")
    private int idUsuario;

//    @Column(name="ID_TIPO_INCIDENCIA")
//    private int idTipoIncidencia;

    @Column(name="ID_NOTA")
    private int idNota;

    @Column(name="ID_TECNICO")
    private int idTecnico;

//    @Column(name="ID_PRIORIDAD")
//    private int idPrioriodad;

//    @OneToOne
//    @JoinColumn(name = "idprioridadincidencia", unique = true)
//    private PrioridadIncidencia prioridadIncidencia;

//    @OneToOne
//    @JoinColumn(name="idtipoincidencia", unique = true)
//    private TipoIncidencia tipoIncidencia;

//    @OneToOne
//    @JoinColumn(name="idestadoincidencia", unique = true)
//    private EstadoIncidencia estadoIncidencia;

    @OneToOne
    @JoinColumn(name = "ID_ESTADO_INCIDENCIA", referencedColumnName = "ID_ESTADO_INCIDENCIA", unique = true)
    private EstadoIncidencia estadoIncidencia;

    @OneToOne
    @JoinColumn(name = "ID_PRIORIDAD", referencedColumnName = "ID_PRIORIDAD", unique = true)
    private PrioridadIncidencia prioridadIncidencia;

    @OneToOne
    @JoinColumn(name = "ID_TIPO_INCIDENCIA", referencedColumnName = "ID_TIPO_INCIDENCIA", unique = true)
    private TipoIncidencia tipoIncidencia;

    @OneToMany(mappedBy = "incidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaIncidencia> listaNotaIncidencias;


}
