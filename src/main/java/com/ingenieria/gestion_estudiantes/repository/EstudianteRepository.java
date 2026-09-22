package com.ingenieria.gestion_estudiantes.repository; 
 
import com.ingenieria.gestion_estudiantes.model.Estudiante; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 
import java.util.Optional; 
 
@Repository 
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> { 
    Optional<Estudiante> findByEmail(String email); 
    boolean existsByCodigo(String codigo); 
    boolean existsByCodigoAndIdNot(String codigo, Long id);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}
