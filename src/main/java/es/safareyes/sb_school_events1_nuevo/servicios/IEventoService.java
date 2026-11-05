package es.safareyes.sb_school_events1_nuevo.servicios;

import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqEventoDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResEventoDetalleDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResEventoResumenDTO;

import java.util.List;

public interface IEventoService {
    List<ResEventoResumenDTO> listar(Integer categoriaId, String estado);
    ResEventoDetalleDTO buscarPorId(Integer id);
    ResEventoResumenDTO crear(ReqEventoDTO request);
    ResEventoResumenDTO actualizar(Integer id, ReqEventoDTO request);
    void eliminar(Integer id);
}