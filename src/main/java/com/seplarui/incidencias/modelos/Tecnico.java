package com.seplarui.incidencias.modelos;


import jakarta.persistence.*;

@Entity
@Table(name="TECNICOS")
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_TECNICO")
    private Long idTecnico;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @OneToOne
    @JoinColumn(name = "iddepartamento", unique = true)
    private Departamento departamento;

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
