package com.eoi.CitaTe.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "disponibilidad")
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_disponibilidad", nullable = false)
    private Long id;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate fecha;

// en lugar de por nombre por nuemero desde el 0
    private String diaDeLaSemana;
    private String horaInicioManiana;
    private String horaFinManiana;
    private String horaInicioTarde;
    private String horaFinTarde;


    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id_empleado")
    private Empleado empleado;

}