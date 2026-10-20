package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Evento_requisito;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEvento_requisito extends JpaRepository<Evento_requisito, Integer> {

    @EntityGraph(attributePaths = {"evento", "requisito"})
    List<Evento_requisito> findByEvento_Id(Integer eventoId);

    @EntityGraph(attributePaths = {"evento", "requisito"})
    List<Evento_requisito> findByRequisito_Id(Integer requisitoId);
}
