package es.safareyes.sb_school_events1_nuevo.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

public record ResPublicacionDTO(
        Long id,
        Long eventoId,
        String autorNombre,
        String texto,
        LocalDateTime creadaEn,
        List<String> fotosUrls
) {}