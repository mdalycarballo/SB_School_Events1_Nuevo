package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp; // Import correcto para JPA / JDBC
import java.util.List;

@Repository
public interface IEvento extends JpaRepository<Evento, Integer> {

    @EntityGraph(attributePaths = {"categoria", "creador", "lugar"})
    List<Evento> findByCategoria_Id(Integer categoriaId);

    @EntityGraph(attributePaths = {"categoria", "creador", "lugar"})
    List<Evento> findByCreador_Id(Integer creadorId);

    @EntityGraph(attributePaths = {"categoria", "creador", "lugar"})
    List<Evento> findByLugar_Id(Integer lugarId);

    @EntityGraph(attributePaths = {"categoria", "creador", "lugar"})
    List<Evento> findByFechaInicio(Timestamp fechaInicio);

    @EntityGraph(attributePaths = {"categoria", "creador", "lugar"})
    List<Evento> findByFechaFin(Timestamp fechaFin);

    @EntityGraph(attributePaths = {"categoria", "creador", "lugar"})
    List<Evento> findByTituloContainingIgnoreCase(String titulo);

    @EntityGraph(attributePaths = {"categoria", "creador", "lugar"})
    List<Evento> findByEstado(String estado);
}
