package es.safareyes.sb_school_events1_nuevo.servicios;

import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqUsuarioDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResUsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    List<ResUsuarioDTO> listar();
    ResUsuarioDTO buscarPorId(Integer id);
    ResUsuarioDTO crear(ReqUsuarioDTO request);
}