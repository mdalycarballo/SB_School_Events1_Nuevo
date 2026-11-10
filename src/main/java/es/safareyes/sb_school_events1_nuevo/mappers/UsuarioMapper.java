package es.safareyes.sb_school_events1_nuevo.mappers;

import es.safareyes.sb_school_events1_nuevo.modelos.Usuario;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqUsuarioDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResUsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "username", source = "nombre")
    @Mapping(target = "nombreCompleto", source = "nombre")
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "cursoNombre", ignore = true)
    ResUsuarioDTO aResponse(Usuario usuario);

    List<ResUsuarioDTO> aResponseList(List<Usuario> usuarios);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaInscripcion", ignore = true)
    @Mapping(target = "curso", ignore = true)
    Usuario aEntidad(ReqUsuarioDTO request);
}