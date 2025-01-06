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

    public Long getIdEstadoIncidencia() {
        return idEstadoIncidencia;
    }

    public void setIdEstadoIncidencia(Long idEstadoIncidencia) {
        this.idEstadoIncidencia = idEstadoIncidencia;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
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
