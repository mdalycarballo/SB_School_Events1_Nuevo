package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Cambio_evento;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICambio_evento extends JpaRepository<Cambio_evento, Integer> {

    @EntityGraph(attributePaths = {"evento"})
    List<Cambio_evento> findByEvento_Id(Integer eventoId);
}