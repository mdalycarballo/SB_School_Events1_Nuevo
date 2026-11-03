package es.safareyes.sb_school_events1_nuevo.mapper;

import es.safareyes.sb_school_events1_nuevo.dto.request.ReqCategoriaDTO;
import es.safareyes.sb_school_events1_nuevo.dto.response.ResCategoriaDTO;
import es.safareyes.sb_school_events1_nuevo.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    ResCategoriaDTO aResponse(Categoria categoria);
    List<ResCategoriaDTO> aResponseList(List<Categoria> categorias);

    @Mapping(target = "id", ignore = true)
    Categoria aEntidad(ReqCategoriaDTO request);
}
