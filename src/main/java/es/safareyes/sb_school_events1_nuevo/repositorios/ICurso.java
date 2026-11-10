package es.safareyes.sb_school_events1_nuevo.repositorios;

import es.safareyes.sb_school_events1_nuevo.modelos.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICurso extends JpaRepository<Curso, Integer> {

    List<Curso> findByNivel(String nivel);

    List<Curso> findByNombreContainingIgnoreCase(String nombre);
}
