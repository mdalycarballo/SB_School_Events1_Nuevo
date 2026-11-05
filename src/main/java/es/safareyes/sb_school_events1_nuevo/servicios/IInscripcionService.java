package es.safareyes.sb_school_events1_nuevo.servicios;

import es.safareyes.sb_school_events1_nuevo.dtos.response.ResInscripcionDTO;

import java.util.List;

public interface IInscripcionService {
    ResInscripcionDTO inscribirUsuario(Integer eventoId, Integer usuarioId, String tipo);
    void cancelarInscripcion(Integer inscripcionId);
    List<ResInscripcionDTO> listarPorEvento(Integer eventoId);
    List<ResInscripcionDTO> listarPorUsuario(Integer usuarioId);
}