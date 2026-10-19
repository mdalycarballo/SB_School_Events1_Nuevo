package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Inscripcion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInscripcion extends JpaRepository<Inscripcion, Integer> {
    List<Inscripcion> findByUsuario_IdAndTipo(Integer usuarioId, String tipo);
    List<Inscripcion> findByUsuario_Id(Integer usuarioId);
    Page<Inscripcion> findByEvento_Id(Integer eventoId, Pageable pageable);
}