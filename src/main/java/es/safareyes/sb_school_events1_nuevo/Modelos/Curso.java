package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "nivel")
    public String nivel;

    @Override
    public String toString() {
        return "Curso{id=" + id + ", nombre='" + nombre + "', nivel='" + nivel + "'}";
    }
}