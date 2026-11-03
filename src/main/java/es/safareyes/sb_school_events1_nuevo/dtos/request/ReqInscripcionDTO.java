package es.safareyes.sb_school_events1_nuevo.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ReqInscripcionDTO(
        @NotNull(message = "El id del evento es obligatorio")
        Long eventoId,

        @NotBlank(message = "El tipo de inscripción es obligatorio")
        @Pattern(regexp = "^(INTERESA|ASISTIRE)$", message = "El tipo debe ser INTERESA o ASISTIRE")
        String tipo
) {}