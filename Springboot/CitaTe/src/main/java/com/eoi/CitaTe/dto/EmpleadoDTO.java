package com.eoi.CitaTe.dto;

import com.eoi.CitaTe.entities.Disponibilidad;
import com.eoi.CitaTe.entities.Empresa;
import com.eoi.CitaTe.entities.Servicio;
import com.eoi.CitaTe.entities.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {

    private String nombreEmpleado;
    private String apellido1Empleado;
    private String apellido2Empleado;
    private Long empresa_id;
    private Long disponibilidad_id;
    private Set<Servicio> serviciosdto = new HashSet<>();
}
