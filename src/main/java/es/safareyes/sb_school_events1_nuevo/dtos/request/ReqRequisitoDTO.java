package es.safareyes.sb_school_events1_nuevo.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record ReqRequisitoDTO(
        @NotBlank(message = "La descripción del requisito es obligatoria")
        String descripcion
) {}