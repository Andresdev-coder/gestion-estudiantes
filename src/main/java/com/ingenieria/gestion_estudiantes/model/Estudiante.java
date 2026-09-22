package com.ingenieria.gestion_estudiantes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estudiantes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "codigo_estudiante", nullable = false, unique = true, length = 20)
    private String codigo;
}