package com.seplarui.incidencias.modelos;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="NOTAS_INCIDENCIA")
public class NotaIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDNOTAINCIDENCIA")
    private Long idNotaIncidencia;

    @Column(name = "DESCRIPCION")
    private String nota;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "IDINCIDENCIA", nullable = false)
    private Incidencia incidencia;


    public Long getIdNotaIncidencia() {
        return idNotaIncidencia;
    }

    public void setIdNotaIncidencia(Long idNotaIncidencia) {
        this.idNotaIncidencia = idNotaIncidencia;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }
}
