package es.safareyes.sb_school_events1_nuevo.dtos.response;

import java.time.LocalDateTime;
import java.util.Set;

public record ResEventoDetalleDTO(
        Long id,
        String titulo,
        String descripcion,
        String requisitos,
        LocalDateTime inicio,
        LocalDateTime fin,
        ResLugarDTO lugar,
        ResCategoriaDTO categoria,
        Integer aforoMaximo,
        Integer plazasDisponibles,
        LocalDateTime fechaLimiteInscripcion,
        String estado,
        String organizadorNombre,
        Set<String> cursosNombres
) {}