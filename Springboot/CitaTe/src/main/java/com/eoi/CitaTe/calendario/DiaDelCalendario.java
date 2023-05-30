package com.eoi.CitaTe.calendario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiaDelCalendario {

    private boolean diaNulo = false;

    private List<Evento> eventos;

    private LocalDate fecha;




}
