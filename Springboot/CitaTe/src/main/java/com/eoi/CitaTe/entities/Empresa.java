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
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empresa", nullable = false)
    private Long id;

    private String nombreEmpresa;
    private String cif;
    @Embedded
    private Direccion direccion;
    private String descripcionEmpresa;
    @Embedded
    private Contacto contacto;
    @Lob
    private byte[] logoEmpresa;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private Set<Empleado> empleados = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalogoDeServicio_id", referencedColumnName = "id_catalogoDeServicio")
    private CatalogoDeServicio catalogoDeServicio;



//    private Set<String> tipoEmpresa = new HashSet;
//    private MetodoPagoMensual metodoPagoMensual;
//    private Pago pago;


    public void addEmpleado(Empleado empleado){
        empleados.add(empleado);
        empleado.setEmpresa(this);
    }
}