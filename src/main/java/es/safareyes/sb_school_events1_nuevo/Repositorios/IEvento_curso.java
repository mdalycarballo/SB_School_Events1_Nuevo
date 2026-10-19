package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Evento_curso;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEvento_curso extends JpaRepository<Evento_curso, Integer> {
    List<Evento_curso> findByEvento_Id(Integer eventoId);
    boolean existsByEvento_IdAndCurso_Id(Integer eventoId, Integer cursoId);
}
