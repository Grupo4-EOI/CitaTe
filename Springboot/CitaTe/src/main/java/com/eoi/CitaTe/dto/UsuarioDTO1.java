package com.eoi.CitaTe.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO1 {
    private Long id;
    private String email;
    private String pass;
    private boolean activo;

    String tipoAlta;
    private ClienteDTONuevo clienteDTONuevo;

    private EmpleadoDTONuevo empleadoDTONuevo;


}
