package es.safareyes.sb_school_events1_nuevo.dtos.response;

import java.time.LocalDateTime;

public record ResFotoPublicacionDTO(
        Long id,
        Long publicacionId,
        String url,
        String nombreOriginal,
        LocalDateTime subidaEn
) {}