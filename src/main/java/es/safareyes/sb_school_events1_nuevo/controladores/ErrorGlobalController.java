package es.safareyes.sb_school_events1_nuevo.controladores;

import es.safareyes.sb_school_events1_nuevo.exception.RecursoNoEncontradoException;
import es.safareyes.sb_school_events1_nuevo.exception.ReglaNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorGlobalController {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ProblemDetail noEncontrado(RecursoNoEncontradoException e) {
        return construir(HttpStatus.NOT_FOUND, "Recurso no encontrado", e.getMessage());
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ProblemDetail reglaNegocio(ReglaNegocioException e) {
        return construir(HttpStatus.UNPROCESSABLE_ENTITY, "Regla de negocio incumplida", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validacionEntrada(MethodArgumentNotValidException e) {
        String detalle = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return construir(HttpStatus.BAD_REQUEST, "Error de validación", detalle);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail errorGenerico(Exception e) {
        return construir(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", e.getMessage());
    }

    private ProblemDetail construir(HttpStatus estado, String titulo, String detalle) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(estado, detalle);
        problema.setTitle(titulo);
        problema.setProperty("timestamp", LocalDateTime.now());
        return problema;
    }
}