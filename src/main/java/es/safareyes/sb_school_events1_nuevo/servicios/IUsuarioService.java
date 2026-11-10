package es.safareyes.sb_school_events1_nuevo.servicios;

import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqUsuarioDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResUsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    List<ResUsuarioDTO> obtenerTodos();
    ResUsuarioDTO obtenerPorId(Integer id);
    ResUsuarioDTO registrarUsuario(ReqUsuarioDTO dto);
    ResUsuarioDTO actualizarUsuario(Integer id, ReqUsuarioDTO dto);
    void cambiarEstado(Integer id, String nuevoEstado);
    void eliminarUsuario(Integer id);
}