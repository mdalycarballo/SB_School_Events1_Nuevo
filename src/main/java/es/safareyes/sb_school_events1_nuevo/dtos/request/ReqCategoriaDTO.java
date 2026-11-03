package es.safareyes.sb_school_events1_nuevo.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReqCategoriaDTO(
        @NotBlank(message = "El nombre de la categoría es obligatorio")
        @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
        String nombre,

        @NotBlank(message = "El icono es obligatorio")
        String icono,

        @NotBlank(message = "El color es obligatorio")
        String color
) {}