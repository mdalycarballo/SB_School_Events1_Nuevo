package es.safareyes.sb_school_events1_nuevo.mappers;

import es.safareyes.sb_school_events1_nuevo.Modelos.Inscripcion;
import es.safareyes.sb_school_events1_nuevo.dtos.response.ResInscripcionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {

    @Mapping(target = "eventoId", source = "evento.id")
    @Mapping(target = "eventoTitulo", source = "evento.titulo")
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "usuarioNombre", source = "usuario.nombre")
    @Mapping(target = "fechaInscripcion", source = "fechaInscripcion")
    ResInscripcionDTO aResponse(Inscripcion inscripcion);

    List<ResInscripcionDTO> aResponseList(List<Inscripcion> inscripciones);

    default LocalDateTime map(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }

    default Timestamp map(LocalDateTime value) {
        return value == null ? null : Timestamp.valueOf(value);
    }
}