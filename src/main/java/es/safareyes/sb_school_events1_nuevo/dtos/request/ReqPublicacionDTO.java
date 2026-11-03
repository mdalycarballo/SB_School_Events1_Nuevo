package es.safareyes.sb_school_events1_nuevo.dtos   .request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReqPublicacionDTO(
        @NotBlank(message = "El texto de la publicación es obligatorio")
        @Size(min = 1, max = 2000, message = "El texto debe tener entre 1 y 2000 caracteres")
        String texto
) {}