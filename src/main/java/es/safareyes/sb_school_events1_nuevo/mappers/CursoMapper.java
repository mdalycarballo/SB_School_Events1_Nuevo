package es.safareyes.sb_school_events1_nuevo.mappers;

import es.safareyes.sb_school_events1_nuevo.modelos.Curso;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqCursoDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResCursoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    ResCursoDTO aResponse(Curso curso);

    List<ResCursoDTO> aResponseList(List<Curso> cursos);

    @Mapping(target = "id", ignore = true)
    Curso aEntidad(ReqCursoDTO request);
}