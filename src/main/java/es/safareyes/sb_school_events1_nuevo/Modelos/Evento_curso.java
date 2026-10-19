package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "evento_curso")
public class Evento_curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    public Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    public Curso curso;

    @Override
    public String toString() {
        return "Evento_curso{id=" + id + ", evento=" + (evento != null ? evento.titulo : "null") + ", curso=" + (curso != null ? curso.nombre : "null") + "}";
    }
}
