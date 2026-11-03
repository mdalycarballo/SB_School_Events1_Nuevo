package es.safareyes.sb_school_events1_nuevo.dtos.response;

import java.time.LocalDateTime;

public record ResEventoResumenDTO(
        Long id,
        String titulo,
        LocalDateTime inicio,
        LocalDateTime fin,
        String lugarNombre,
        String categoriaNombre,
        String categoriaIcono,
        String categoriaColor,
        String estado
) {}