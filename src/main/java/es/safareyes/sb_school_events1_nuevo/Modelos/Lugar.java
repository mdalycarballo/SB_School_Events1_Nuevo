package es.safareyes.sb_school_events1_nuevo.Modelos;


import jakarta.persistence.*;

@Entity
@Table(name = "lugar")
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "aforo_max")
    public Integer aforoMax;

    @Override
    public String toString() {
        return "Lugar{id=" + id + ", nombre='" + nombre + "', aforoMax=" + aforoMax + "}";
    }
}
