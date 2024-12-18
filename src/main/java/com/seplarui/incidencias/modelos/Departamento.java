package com.seplarui.incidencias.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="DEPARTAMENTOS")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDEPARTAMENTO")
    private Long idDepartamento;

    @Column(name="CODDEPARTAMENTO")
    private String codDepartamento;

    @Column(name="DESCRIPCION")
    private String departamento;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tecnico> listaTecnicos;

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Tecnico> getListaTecnicos() {
        return listaTecnicos;
    }

    public void setListaTecnicos(List<Tecnico> listaTecnicos) {
        this.listaTecnicos = listaTecnicos;
    }
}
