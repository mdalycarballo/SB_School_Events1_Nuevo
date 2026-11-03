package es.safareyes.sb_school_events1_nuevo.dtos.response;

public record ResUsuarioDTO(
        Long id,
        String username,
        String email,
        String nombreCompleto,
        String rol,
        String cursoNombre
) {}