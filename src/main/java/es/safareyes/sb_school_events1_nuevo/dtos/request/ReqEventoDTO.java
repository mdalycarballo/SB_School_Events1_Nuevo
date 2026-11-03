package es.safareyes.sb_school_events1_nuevo.dtos.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

public record ReqEventoDTO(
        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "La descripción es obligatoria")
        String descripcion,

        String requisitos,

        @NotNull(message = "La fecha y hora de inicio es obligatoria")
        @Future(message = "La fecha de inicio debe ser futura")
        LocalDateTime inicio,

        @NotNull(message = "La fecha y hora de fin es obligatoria")
        @Future(message = "La fecha de fin debe ser futura")
        LocalDateTime fin,

        @NotNull(message = "Debes indicar un lugar")
        Long lugarId,

        @NotNull(message = "Debes indicar una categoría")
        Long categoriaId,

        @Min(value = 1, message = "El aforo máximo debe ser al menos 1")
        Integer aforoMaximo,

        LocalDateTime fechaLimiteInscripcion,

        Set<Long> cursosIds
) {}