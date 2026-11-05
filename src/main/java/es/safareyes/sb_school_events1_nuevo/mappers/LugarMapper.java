package es.safareyes.sb_school_events1_nuevo.mappers;

import es.safareyes.sb_school_events1_nuevo.Modelos.Lugar;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqLugarDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResLugarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LugarMapper {

    @Mapping(target = "aforo", source = "aforoMax")
    ResLugarDTO aResponse(Lugar lugar);

    List<ResLugarDTO> aResponseList(List<Lugar> lugares);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aforoMax", source = "aforo")
    Lugar aEntidad(ReqLugarDTO request);
}