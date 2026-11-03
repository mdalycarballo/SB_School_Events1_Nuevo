package es.safareyes.sb_school_events1_nuevo.dtos;
import es.safareyes.sb_school_events1_nuevo.Modelos.Publicacion;
import lombok.Data;

@Data
public class Foto_publicacionDTO {
    private Integer id;
    private Publicacion publicacion;
    private String urlFoto;
    private Integer tamano;
}
