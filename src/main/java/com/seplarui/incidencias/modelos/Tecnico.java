package com.seplarui.incidencias.modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name="USUARIOS")
//public class Usuario {
//
//}


@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="TECNICOS")
@PrimaryKeyJoinColumn(name = "IDUSUARIO")
public class Tecnico extends Usuario{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="IDTECNICO")
//    private Long idTecnico;

//    @ManyToOne
//    @JoinColumn(name="idUsuario", nullable=false)
//    @JsonIgnoreProperties("listaTecnicos")
//    private Usuario usuario;

//    @OneToOne
//    @JoinColumn(name="idUsuario", nullable=false)
//    @JsonIgnoreProperties("tecnico")
//    private Usuario usuario;


//    @Column(name="NOMBRE")
//    private String nombre;
//
//    @Column(name="APELLIDOS")
//    private String apellidos;

    @ManyToOne
    @JoinColumn(name = "idDepartamento", nullable = true)
    @JsonIgnoreProperties("listaTecnicos")
    private Departamento departamento;

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> listaIncidencias;

//    public Long getIdTecnico() {
//        return idTecnico;
//    }
//
//    public void setIdTecnico(Long idTecnico) {
//        this.idTecnico = idTecnico;
//    }

//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getApellidos() {
//        return apellidos;
//    }
//
//    public void setApellidos(String apellidos) {
//        this.apellidos = apellidos;
//    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }


//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

}
