package es.safareyes.sb_school_events1_nuevo.mappers;

import es.safareyes.sb_school_events1_nuevo.Modelos.Categoria;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqCategoriaDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResCategoriaDTO;
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