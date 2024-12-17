package com.seplarui.incidencias.modelos;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="TECNICOS")
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDTECNICO")
    private Long idTecnico;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="APELLIDOS")
    private String apellidos;

    @ManyToOne
    @JoinColumn(name = "idDepartamento", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> listaIncidencias;

    public Long getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Long idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
