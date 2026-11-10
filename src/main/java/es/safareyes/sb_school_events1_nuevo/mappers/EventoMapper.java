package es.safareyes.sb_school_events1_nuevo.mappers;

import es.safareyes.sb_school_events1_nuevo.modelos.Evento;
import es.safareyes.sb_school_events1_nuevo.dtos.request.ReqEventoDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResEventoDetalleDTO;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResEventoResumenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        uses = {LugarMapper.class, CategoriaMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EventoMapper {

    @Mapping(target = "inicio", source = "fechaInicio")
    @Mapping(target = "fin", source = "fechaFin")
    @Mapping(target = "lugarNombre", source = "lugar.nombre")
    @Mapping(target = "categoriaNombre", source = "categoria.nombre")
    @Mapping(target = "categoriaIcono", source = "categoria.icono")
    @Mapping(target = "categoriaColor", source = "categoria.color")
    ResEventoResumenDTO aResumenDTO(Evento evento);

    @Mapping(target = "inicio", source = "fechaInicio")
    @Mapping(target = "fin", source = "fechaFin")
    @Mapping(target = "organizadorNombre", source = "creador.nombre")
    @Mapping(target = "plazasDisponibles", ignore = true)
    @Mapping(target = "cursosNombres", ignore = true)
    @Mapping(target = "requisitos", ignore = true)
    @Mapping(target = "fechaLimiteInscripcion", ignore = true)
    ResEventoDetalleDTO aDetalleDTO(Evento evento);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaInicio", source = "inicio")
    @Mapping(target = "fechaFin", source = "fin")
    @Mapping(target = "lugar", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "creador", ignore = true)
    Evento aEntidad(ReqEventoDTO request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaInicio", source = "inicio")
    @Mapping(target = "fechaFin", source = "fin")
    @Mapping(target = "lugar", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "creador", ignore = true)
    void actualizarEntidad(@MappingTarget Evento destino, ReqEventoDTO request);

    default Timestamp map(LocalDateTime value) {
        return value == null ? null : Timestamp.valueOf(value);
    }

    default LocalDateTime map(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }
}