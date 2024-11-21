package com.seplarui.incidencias.modelos;


import jakarta.persistence.*;

@Entity
@Table(name="NOTAS_INCIDENCIA")
public class NotaIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NOTA_INCIDENCIA")
    private Long idNotaIncidencia;

    @Column(name="NOTA", nullable = false)
    private String nota;

    @ManyToOne
    @JoinColumn(name = "ID_INCIDENCIA", nullable = false)
    private Incidencia incidencia;
}
