package es.safareyes.sb_school_events1_nuevo.mappers;

import es.safareyes.sb_school_events1_nuevo.modelos.Publicacion;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqPublicacionDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResPublicacionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PublicacionMapper {

    @Mapping(target = "eventoId", source = "evento.id")
    @Mapping(target = "autorNombre", source = "usuario.nombre")
    @Mapping(target = "creadaEn", source = "fecha")
    ResPublicacionDTO aResponse(Publicacion publicacion);

    List<ResPublicacionDTO> aResponseList(List<Publicacion> publicaciones);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evento", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    Publicacion aEntidad(ReqPublicacionDTO request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evento", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    void actualizarEntidad(@MappingTarget Publicacion destino, ReqPublicacionDTO request);

    default Timestamp map(LocalDateTime value) {
        return value == null ? null : Timestamp.valueOf(value);
    }

    default LocalDateTime map(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }
}