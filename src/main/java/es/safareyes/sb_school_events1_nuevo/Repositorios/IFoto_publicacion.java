package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Foto_publicacion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoto_publicacion extends JpaRepository<Foto_publicacion, Integer> {

    @EntityGraph(attributePaths = {"publicacion"})
    List<Foto_publicacion> findByPublicacion_Id(Integer publicacionId);

    @EntityGraph(attributePaths = {"publicacion"})
    List<Foto_publicacion> findByUrlFotoContainingIgnoreCase(String urlFoto);
}
