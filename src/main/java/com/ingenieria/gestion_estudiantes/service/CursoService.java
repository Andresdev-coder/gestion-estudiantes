package com.ingenieria.gestion_estudiantes.service;

import com.ingenieria.gestion_estudiantes.model.Curso;
import com.ingenieria.gestion_estudiantes.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Curso registrar(Curso curso) {
        validar(curso);
        if (repository.existsByCodigo(curso.getCodigo())) {
            throw new IllegalArgumentException("El código de curso ya está registrado.");
        }
        return repository.save(curso);
    }

    @Transactional(readOnly = true)
    public Curso obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado: " + id));
    }

    @Transactional
    public Curso actualizar(Long id, Curso datos) {
        Curso existente = obtenerPorId(id);
        validar(datos);
        if (repository.existsByCodigoAndIdNot(datos.getCodigo(), id)) {
            throw new IllegalArgumentException("El código de curso ya está registrado.");
        }
        existente.setNombre(datos.getNombre());
        existente.setCodigo(datos.getCodigo());
        existente.setCreditos(datos.getCreditos());
        existente.setCupoMaximo(datos.getCupoMaximo());
        existente.setDocente(datos.getDocente());
        return repository.save(existente);
    }

    private void validar(Curso curso) {
        if (curso.getNombre() == null || curso.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre del curso es obligatorio.");
        if (curso.getCodigo() == null || curso.getCodigo().isBlank())
            throw new IllegalArgumentException("El código del curso es obligatorio.");
        if (curso.getCreditos() == null || curso.getCreditos() < 1)
            throw new IllegalArgumentException("Los créditos deben ser al menos 1.");
        if (curso.getCupoMaximo() == null || curso.getCupoMaximo() < 1)
            throw new IllegalArgumentException("El cupo máximo debe ser al menos 1.");
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe el ID: " + id);
        }
        repository.deleteById(id);
    }
}
