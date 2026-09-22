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
        validar(estudiante);
        if (repository.existsByCodigo(estudiante.getCodigo())) {
            throw new IllegalArgumentException("El código de estudiante ya se encuentra registrado.");
        }
        if (repository.existsByEmailIgnoreCase(estudiante.getEmail())) {
            throw new IllegalArgumentException("El correo ya se encuentra registrado.");
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
        validar(datos);
        if (repository.existsByCodigoAndIdNot(datos.getCodigo(), id)) {
            throw new IllegalArgumentException("El código de estudiante ya se encuentra registrado.");
        }
        if (repository.existsByEmailIgnoreCaseAndIdNot(datos.getEmail(), id)) {
            throw new IllegalArgumentException("El correo ya se encuentra registrado.");
        }
        existente.setNombre(datos.getNombre());
        existente.setEmail(datos.getEmail());
        existente.setCodigo(datos.getCodigo());
        return repository.save(existente);
    }

    private void validar(Estudiante estudiante) {
        if (estudiante.getNombre() == null || estudiante.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio.");
        if (estudiante.getCodigo() == null || estudiante.getCodigo().isBlank())
            throw new IllegalArgumentException("El código es obligatorio.");
        if (estudiante.getEmail() == null || !estudiante.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"))
            throw new IllegalArgumentException("Ingresa un correo electrónico válido.");
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe el ID: " + id);
        }
        repository.deleteById(id);
    }
}
