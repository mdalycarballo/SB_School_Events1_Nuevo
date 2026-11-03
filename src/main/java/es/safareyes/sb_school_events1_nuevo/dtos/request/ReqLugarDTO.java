package es.safareyes.sb_school_events1_nuevo.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReqLugarDTO(
        @NotBlank(message = "El nombre del lugar es obligatorio")
        String nombre,

        @NotNull(message = "El aforo máximo es obligatorio")
        @Min(value = 1, message = "El aforo debe ser al menos de 1 persona")
        Integer aforo
) {}