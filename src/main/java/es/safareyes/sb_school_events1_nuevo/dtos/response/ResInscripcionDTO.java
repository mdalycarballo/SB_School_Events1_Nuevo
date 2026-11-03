package es.safareyes.sb_school_events1_nuevo.dtos.response;

import java.time.LocalDateTime;

public record ResInscripcionDTO(
        Long id,
        Long usuarioId,
        String usuarioNombre,
        Long eventoId,
        String eventoTitulo,
        LocalDateTime fechaInscripcion,
        String estado,
        String tipo,
        Boolean asistio
) {}