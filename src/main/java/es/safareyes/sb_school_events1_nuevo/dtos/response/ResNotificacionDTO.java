package es.safareyes.sb_school_events1_nuevo.dtos.response;

import java.time.LocalDateTime;

public record ResNotificacionDTO(
        Long id,
        String tipo,
        String mensaje,
        String recurso,
        Long recursoId,
        Boolean leida,
        LocalDateTime creadaEn
) {}