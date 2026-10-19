package es.safareyes.sb_school_events1_nuevo.Repositorios;

import es.safareyes.sb_school_events1_nuevo.Modelos.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificacion extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByUsuario_IdOrderByFechaDesc(Integer usuarioId);
    List<Notificacion> findByUsuario_IdAndLeidaFalse(Integer usuarioId);
}