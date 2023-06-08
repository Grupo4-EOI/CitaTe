package com.eoi.CitaTe.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empleado", nullable = false)
    private Long id;
    private String nombreEmpleado;
    private String apellido1Empleado;
    private String apellido2Empleado;


    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id_empresa")
    private Empresa empresa;


    @OneToOne(mappedBy ="empleado")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disponibilidad_id", referencedColumnName = "id_disponibilidad")
    private Disponibilidad disponibilidad;


    @ManyToMany(mappedBy = "empleados", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Servicio> servicios = new HashSet<>();


    public void addServicio(Servicio servicio){
        servicios.add(servicio);
        servicio.getEmpleados().add(this);
    }

}