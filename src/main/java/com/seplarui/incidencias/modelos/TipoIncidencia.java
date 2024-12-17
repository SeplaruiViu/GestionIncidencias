package com.seplarui.incidencias.modelos;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TIPOS_INCIDENCIA")
public class TipoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTIPOINCIDENCIA")
    private Long idTipoIncidencia;

    @Column
    private String codTipoIncidencia;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @OneToMany(mappedBy = "tipoIncidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> listaIncidencias;



}
