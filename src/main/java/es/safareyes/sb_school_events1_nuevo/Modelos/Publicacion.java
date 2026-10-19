package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "publicacion")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    public Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    public Usuario usuario;

    @Column(name = "texto")
    public String texto;

    @Column(name = "resultados")
    public String resultados;

    @Column(name = "fecha")
    public Timestamp fecha;

    @Override
    public String toString() {
        return "Publicacion{id=" + id + ", contenido='" + texto + "', autor=" + (usuario != null ? usuario.nombre : "null") + ", evento=" + (evento != null ? evento.titulo : "null") + "}";
    }
}
