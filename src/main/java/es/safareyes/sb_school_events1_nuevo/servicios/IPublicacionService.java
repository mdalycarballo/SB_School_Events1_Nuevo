package es.safareyes.sb_school_events1_nuevo.servicios;

import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqPublicacionDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResPublicacionDTO;

import java.util.List;

public interface IPublicacionService {
    List<ResPublicacionDTO> obtenerTodas();
    List<ResPublicacionDTO> obtenerPorEvento(Integer eventoId);
    List<ResPublicacionDTO> obtenerPorUsuario(Integer usuarioId);
    ResPublicacionDTO obtenerPorId(Integer id);
    ResPublicacionDTO crearPublicacion(Integer eventoId, Integer usuarioId, ReqPublicacionDTO dto);
    ResPublicacionDTO actualizarPublicacion(Integer id, ReqPublicacionDTO dto, Integer usuarioId);
    void eliminarPublicacion(Integer id, Integer usuarioId);
}