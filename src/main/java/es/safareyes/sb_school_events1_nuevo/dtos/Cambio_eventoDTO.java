package es.safareyes.sb_school_events1_nuevo.dtos;

import es.safareyes.sb_school_events1_nuevo.Modelos.Evento;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Cambio_eventoDTO {
    private Integer id;
    private Evento eventos;
    private String cambios;
    private Timestamp fechas;
}
