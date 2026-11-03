package es.safareyes.sb_school_events1_nuevo.dtos.response;

public record ResInformeParticipacionDTO(
        String categoriaNombre,
        String cursoNombre,
        Long totalEventos,
        Long totalAsistentesConfirmados
) {}