package com.eoi.CitaTe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {

    private String calle;
    private String numero;
    private String provincia;
    private String ciudad;
    private String codigoPostal;
}
