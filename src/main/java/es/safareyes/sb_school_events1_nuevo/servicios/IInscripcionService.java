package es.safareyes.sb_school_events1_nuevo.servicios;

import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqInscripcionDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResInscripcionDTO;

import java.util.List;

public interface IInscripcionService {
    ResInscripcionDTO inscribirse(ReqInscripcionDTO dto, Integer usuarioId);
    List<ResInscripcionDTO> obtenerPorUsuario(Integer usuarioId);
    List<ResInscripcionDTO> obtenerPorEvento(Integer eventoId);
    ResInscripcionDTO cambiarEstado(Integer id, String nuevoEstado);
    ResInscripcionDTO registrarAsistencia(Integer id, Boolean asistio);
    void cancelarInscripcion(Integer id, Integer usuarioId);
}