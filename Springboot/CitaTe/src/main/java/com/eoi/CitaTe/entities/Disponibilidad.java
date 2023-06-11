package com.eoi.CitaTe.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
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
    private String descri;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private Time hora_inicio;
    private Time hora_fin;


   @OneToOne(mappedBy = "disponibilidad")
   private Empleado empleado;

}