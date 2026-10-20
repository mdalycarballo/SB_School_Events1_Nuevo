package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Notificacion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificacion extends JpaRepository<Notificacion, Integer> {

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Notificacion> findByUsuario_Id(Integer usuarioId);

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Notificacion> findByEvento_Id(Integer eventoId);

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Notificacion> findByLeida(Boolean leida);

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Notificacion> findByTipo(String tipo);
}