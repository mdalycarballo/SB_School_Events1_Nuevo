package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Inscripcion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IInscripcion extends JpaRepository<Inscripcion, Integer> {

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Inscripcion> findByUsuario_Id(Integer usuarioId);

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Inscripcion> findByEvento_Id(Integer eventoId);

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Inscripcion> findByEstado(String estado);

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Inscripcion> findByTipo(String tipo);

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Inscripcion> findByAsistio(Boolean asistio);

    @EntityGraph(attributePaths = {"usuario", "evento"})
    List<Inscripcion> findByFechaInscripcion(Timestamp fechaInscripcion);
}