package com.seplarui.incidencias.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<NotaIncidencia> listaNotas;

    @ManyToOne
    @JoinColumn(name = "IDTECNICO", nullable = false)
    private Tecnico tecnico;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "IDESTADOINCIDENCIA")
    private EstadoIncidencia estadoIncidencia;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="IDPRIORIDADINCIDENCIA")
    private PrioridadIncidencia prioridadIncidencia;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "IDTIPOINCIDENCIA")
    private TipoIncidencia tipoIncidencia;

    public TipoIncidencia getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }

    public PrioridadIncidencia getPrioridadIncidencia() {
        return prioridadIncidencia;
    }

    public void setPrioridadIncidencia(PrioridadIncidencia prioridadIncidencia) {
        this.prioridadIncidencia = prioridadIncidencia;
    }

    public EstadoIncidencia getEstadoIncidencia() {
        return estadoIncidencia;
    }

    public void setEstadoIncidencia(EstadoIncidencia estadoIncidencia) {
        this.estadoIncidencia = estadoIncidencia;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public List<NotaIncidencia> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<NotaIncidencia> listaNotas) {
        this.listaNotas = listaNotas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Long idIncidencia) {
        this.idIncidencia = idIncidencia;
    }
}
