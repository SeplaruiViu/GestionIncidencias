package com.seplarui.incidencias.modelos;


import jakarta.persistence.*;

@Entity
@Table(name = "TIPOS_INCIDENCIA")
public class TipoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_INCIDENCIA")
    private Long idTipoIncidencia;

    @Column(name="COD_TIPO_INCIDENCIA")
    private String codTipoIncidencia;

    @Column(name="DESCRIPCION")
    private String descripcion;

      @OneToOne(mappedBy = "tipoIncidencia")
      private Incidencia incidencia;


}
