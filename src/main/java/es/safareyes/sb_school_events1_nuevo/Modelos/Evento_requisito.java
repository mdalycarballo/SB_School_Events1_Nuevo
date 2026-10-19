package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "evento_requisito")
public class Evento_requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    public Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requisito_id", nullable = false)
    public Requisitos requisito;

    @Override
    public String toString() {
        return "Evento_requisito{id=" + id + ", evento=" + (evento != null ? evento.titulo : "null") + ", requisito=" + (requisito != null ? requisito.descripcion : "null") + "}";
    }
}
