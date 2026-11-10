package es.safareyes.sb_school_events1_nuevo.repositorios;

import es.safareyes.sb_school_events1_nuevo.modelos.Publicacion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPublicacion extends JpaRepository<Publicacion, Integer> {

    @EntityGraph(attributePaths = {"evento", "usuario"})
    List<Publicacion> findByEvento_Id(Integer eventoId);

    @EntityGraph(attributePaths = {"evento", "usuario"})
    List<Publicacion> findByUsuario_Id(Integer usuarioId);

    @EntityGraph(attributePaths = {"evento", "usuario"})
    List<Publicacion> findByTextoContainingIgnoreCase(String texto);
}