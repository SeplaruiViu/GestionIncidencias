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

    public Long getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Long idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
