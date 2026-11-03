package es.safareyes.sb_school_events1_nuevo.dtos.response;

import java.time.LocalDateTime;

public record ResCambioEventoDTO(
        Long id,
        Long eventoId,
        String campoModificado,
        String valorAnterior,
        String valorNuevo,
        LocalDateTime fechaCambio,
        String usuarioNombre
) {}