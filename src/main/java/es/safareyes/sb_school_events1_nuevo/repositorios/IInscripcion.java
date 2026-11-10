package es.safareyes.sb_school_events1_nuevo.repositorios;

import es.safareyes.sb_school_events1_nuevo.modelos.Inscripcion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

    // Consultas añadidas para validación de negocio
    Optional<Inscripcion> findByUsuario_IdAndEvento_Id(Integer usuarioId, Integer eventoId);
    long countByEvento_IdAndEstado(Integer eventoId, String estado);
}