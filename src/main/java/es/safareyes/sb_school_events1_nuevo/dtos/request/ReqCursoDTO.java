package es.safareyes.sb_school_events1_nuevo.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReqCursoDTO(
        @NotBlank(message = "El nombre del curso es obligatorio")
        @Size(max = 100, message = "El nombre del curso no puede superar los 100 caracteres")
        String nombre,

        @NotBlank(message = "La etapa es obligatoria (ej: ESO, Bachillerato, FP)")
        String etapa
) {}