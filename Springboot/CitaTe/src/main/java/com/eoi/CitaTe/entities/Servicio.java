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

    private String desc;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Empleados_has_Servicios",
        joinColumns = @JoinColumn(name = "servicio_id", referencedColumnName = "id_servicio"),
        inverseJoinColumns = @JoinColumn(name = "empleado_id", referencedColumnName = "id_empleado"))

    private Set<Empleado> empleados = new HashSet<>();

    //Tiempo que tarda el empleado en ejecutar el servicio del catalogo de servicio
    private int tiempo;



    //private CatalogoDeServicio catalogoDeServicio; // NO HACER FK, PARA EVITAR RELACION CIRCULAR (TEC. MOD. DOM.)


    public void addEmpleado(Empleado empleado){
        empleados.add(empleado);
        empleado.getServicios().add(this);
    }
}