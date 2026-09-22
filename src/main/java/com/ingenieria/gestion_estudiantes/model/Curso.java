package com.ingenieria.gestion_estudiantes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cursos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo_curso", nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false)
    private Integer creditos;

    @Column(name = "cupo_maximo", nullable = false)
    private Integer cupoMaximo;

    @Column(length = 100)
    private String docente;
}
