package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICurso extends JpaRepository<Curso, Integer> {
    List<Curso> findByNivel(String nivel);

    List<Curso> findByNombreContainingIgnoreCase(String nombre);
}
