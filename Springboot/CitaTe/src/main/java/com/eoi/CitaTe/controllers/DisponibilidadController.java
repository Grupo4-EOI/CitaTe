package com.eoi.CitaTe.controllers;

import com.eoi.CitaTe.abstraccomponents.MiControladorGenerico;
import com.eoi.CitaTe.entities.Disponibilidad;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("${url.disponibilidad}")
public class DisponibilidadController extends MiControladorGenerico<Disponibilidad>{

        @Value("${url.disponibilidad}")
        private String urlBase;
        private String entityName = "disponibilidades";

        public DisponibilidadController() {
            super();
        }

    @PostConstruct
    private void init() {
        super.entityName = entityName;
        super.url = url;
    }



    }

