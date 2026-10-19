package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "foto_publicacion")
public class Foto_publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id", nullable = false)
    public Publicacion publicacion;

    @Column(name = "url_foto")
    public String urlFoto;

    @Column(name = "tamano")
    public Integer tamano;

    @Override
    public String toString() {
        return "Foto_publicacion{id=" + id + ", url='" + urlFoto + "', publicacion=" + (publicacion != null ? publicacion.id : "null") + "}";
    }
}



