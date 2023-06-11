package com.eoi.CitaTe.dto;

import com.eoi.CitaTe.entities.Cliente;
import com.eoi.CitaTe.entities.Empleado;
import com.eoi.CitaTe.entities.Empresa;
import com.eoi.CitaTe.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AltaGenericaDto {
    String tipoalta;
    Usuario usuario = new Usuario();
    Cliente cliente = new Cliente();
    Empleado empleado = new Empleado();
    Empresa empresa = new Empresa();

}
