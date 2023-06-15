package com.eoi.CitaTe.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_servicio", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id_empleado")
    Empleado empleado;

    //Tiempo que tarda el empleado en ejecutar el servicio del catalogo de servicio
    private int tiempo;



    //private CatalogoDeServicio catalogoDeServicio; // NO HACER FK, PARA EVITAR RELACION CIRCULAR (TEC. MOD. DOM.)

//
//    public void addEmpleado(Empleado empleado){
//        empleados.add(empleado);
//        empleado.getServicios().add(this);
//    }
}