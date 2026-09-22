package com.ingenieria.gestion_estudiantes.service;

import com.ingenieria.gestion_estudiantes.model.Estudiante;
import com.ingenieria.gestion_estudiantes.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository repository;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Estudiante> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Estudiante registrar(Estudiante estudiante) {
        if (repository.existsByCodigo(estudiante.getCodigo())) {
            throw new IllegalArgumentException("El código ya se encuentra registrado.");
        }
        return repository.save(estudiante);
    }

    @Transactional(readOnly = true)
    public Estudiante obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " +
                        id));
    }

    @Transactional
    public Estudiante actualizar(Long id, Estudiante datos) {
        Estudiante existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setEmail(datos.getEmail());
        existente.setCodigo(datos.getCodigo());
        return repository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe el ID: " + id);
        }
        repository.deleteById(id);
    }
}