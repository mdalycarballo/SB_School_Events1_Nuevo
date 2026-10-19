package es.safareyes.sb_school_events1_nuevo.Modelos;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    public String password;

    @Column(name = "tipo")
    public String tipo;

    @Column(name = "estado")
    public String estado;

    @Column(name = "fecha_inscripcion")
    public Timestamp fechaInscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = true)
    public Curso curso;

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre='" + nombre + "', email='" + email + "', tipo='" + tipo + "', estado='" + estado + "', curso=" + (curso != null ? curso.nombre : "null") + "}";
    }
}
