package es.safareyes.sb_school_events1_nuevo.servicios;

import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqEventoDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResEventoResumenDTO;

import java.util.List;

public interface IEventoService {
    List<ResEventoResumenDTO> obtenerTodos();
    List<ResEventoResumenDTO> obtenerPorEstado(String estado);
    ResEventoResumenDTO obtenerPorId(Integer id);
    ResEventoResumenDTO crearEvento(ReqEventoDTO dto, Integer creadorId);
    ResEventoResumenDTO actualizarEvento(Integer id, ReqEventoDTO dto);
    void cambiarEstado(Integer id, String nuevoEstado);
    void eliminarEvento(Integer id);
}