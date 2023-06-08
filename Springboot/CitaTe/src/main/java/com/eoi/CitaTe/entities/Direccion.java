package com.eoi.CitaTe.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Direccion {

    private String calle;
    private String numero;
    private String provincia;
    private String ciudad;
    private String codigoPostal;
    //private String urlLocalizacion; // Revisar como conseguimos la url de google maps
}