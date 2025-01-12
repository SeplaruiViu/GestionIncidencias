package com.seplarui.incidencias.modelos;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    //@JsonBackReference
    @JsonIgnoreProperties("tipoIncidencia")
    private List<Incidencia> listaIncidencias;

    public Long getIdTipoIncidencia() {
        return idTipoIncidencia;
    }

    public void setIdTipoIncidencia(Long idTipoIncidencia) {
        this.idTipoIncidencia = idTipoIncidencia;
    }

    public String getCodTipoIncidencia() {
        return codTipoIncidencia;
    }

    public void setCodTipoIncidencia(String codTipoIncidencia) {
        this.codTipoIncidencia = codTipoIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Incidencia> getListaIncidencias() {
        return listaIncidencias;
    }

    public void setListaIncidencias(List<Incidencia> listaIncidencias) {
        this.listaIncidencias = listaIncidencias;
    }
}
