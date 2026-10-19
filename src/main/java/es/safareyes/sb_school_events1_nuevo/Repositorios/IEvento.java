package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.util.List;

@Repository
public interface IEvento extends JpaRepository<Evento, Integer> {

    @EntityGraph(attributePaths = {"categoria"})
    List<Evento> findByCategoria_Id(Integer categoriaId);

    @EntityGraph(attributePaths = {"categoria"})
    List<Evento> findByCreador_Id(Integer creadorId);

    @EntityGraph(attributePaths = {"categoria"})
    List<Evento> findByLugar_Id(Integer lugarId);

    List<Evento> findByFechaInicio(Timestamp fechaInicio);

    List<Evento> findByFechaFin(Timestamp fechaFin);

    List<Evento> findByTituloContainingIgnoreCase(String titulo);

    List<Evento> findByEstado(String estado);

}
