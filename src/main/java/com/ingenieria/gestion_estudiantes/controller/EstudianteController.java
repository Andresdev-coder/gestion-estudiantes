package com.ingenieria.gestion_estudiantes.controller; 
 
import com.ingenieria.gestion_estudiantes.model.Estudiante; 
import com.ingenieria.gestion_estudiantes.service.EstudianteService; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
import java.util.List; 
 
@RestController 
@RequestMapping("/api/v1/estudiantes") 
public class EstudianteController { 
 
    private final EstudianteService service; 
 
    public EstudianteController(EstudianteService service) { 
        this.service = service; 
    } 
 
    @GetMapping 
    public ResponseEntity<List<Estudiante>> listar() { 
        return ResponseEntity.ok(service.listarTodos()); 
    } 
 
    @PostMapping 
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) { 
        return new ResponseEntity<>(service.registrar(estudiante), 
HttpStatus.CREATED); 
    } 
 
    @GetMapping("/{id}") 
    public ResponseEntity<Estudiante> buscar(@PathVariable Long id) { 
        return ResponseEntity.ok(service.obtenerPorId(id)); 
    } 
 
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Long id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    } 
} 