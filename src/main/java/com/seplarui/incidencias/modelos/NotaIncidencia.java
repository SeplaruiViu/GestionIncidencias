package com.seplarui.incidencias.modelos;


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

    @ManyToOne
    @JoinColumn(name = "IDINCIDENCIA", nullable = false)
    private Incidencia incidencia;


}
