package com.eoi.CitaTe.controllers;

import com.eoi.CitaTe.abstraccomponents.MiControladorGenerico;
import com.eoi.CitaTe.dto.ClienteDTO;
import com.eoi.CitaTe.dto.EmpleadoDTO;
import com.eoi.CitaTe.dto.UsuarioDTO;
import com.eoi.CitaTe.dto.ValoracionDTO;
import com.eoi.CitaTe.entities.*;
import com.eoi.CitaTe.errorcontrol.exceptions.MiEntidadNoEncontradaException;
import com.eoi.CitaTe.services.DisponibilidadService;
import com.eoi.CitaTe.services.EmpleadoMapperService;
import com.eoi.CitaTe.services.EmpleadoService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@Controller
@RequestMapping("${url.empleado}")
public class EmpleadoController extends MiControladorGenerico<Empleado> {

    @Value("${url.empleado}")
    private String urlBase;
    private String entityName = "empleados";

    public EmpleadoController(){
        super();
    }


    @Autowired
    EmpleadoMapperService empleadoMapperService;

    @Autowired
    DisponibilidadService disponibilidadService;

    @PostConstruct
    private void init() {
        super.entityName = urlBase;
        super.url = entityName + "/";
    }

    @Override
    @GetMapping("/all")
    public String getAll(Model model) {
        this.url = entityName + "/";

// O bien mostramos todas todos los elementos como entidades
        // List<Valoracion> entities = service.listAll();

// o tras mucho trabajo tambien podemos mostrar  como dto
        List<EmpleadoDTO> entities = empleadoMapperService.buscarTodos();


        model.addAttribute("entities", entities);
        return url + "all-entities"; // Nombre de la plantilla para mostrar todas las entidades
    }
    @Override
    @GetMapping("/create")
    public String create(Model model) {
        EmpleadoDTO entity = new EmpleadoDTO();
        model.addAttribute("entity", entity);
        return url + "entity-details";
    }
    @PostMapping(value = {"/actualizar"})
    public String update(@ModelAttribute EmpleadoDTO entity) {
        empleadoMapperService.CrearEmpleado(entity);

        return "redirect:/" + url  + "all";

    }
    @Override
    @GetMapping("/allporempresa")
    public String getById(@PathVariable Object id, Model model) throws MiEntidadNoEncontradaException {
        this.url = entityName + "/";
        try {
            Empleado entity = service.getById(id);
            model.addAttribute("entity", entity);
            return url + "entity-details"; // Nombre de la plantilla para mostrar los detalles de la entidad
        } catch (MiEntidadNoEncontradaException ex) {
            model.addAttribute("mensaje", "Entidad no encontrada");
            model.addAttribute("error", ex.getMessage());
            return "error/error.html"; // Nombre de la plantilla para mostrar la página de error
        }
    }



    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Object id) {
        service.delete(id);
        return "redirect:/" + url +  "all";
    }


    @GetMapping("/{id}")
    public String getByIdempre(@PathVariable Object id, Model model) throws MiEntidadNoEncontradaException {
        this.url = entityName + "/";
        try {
            Empleado entity = service.getById(id);
            model.addAttribute("entity", entity);
            return url + "entity-details"; // Nombre de la plantilla para mostrar los detalles de la entidad
        } catch (MiEntidadNoEncontradaException ex) {
            model.addAttribute("mensaje", "Entidad no encontrada");
            model.addAttribute("error", ex.getMessage());
            return "error/error.html"; // Nombre de la plantilla para mostrar la página de error
        }
    }

    //// Detalles del trabajador
    @GetMapping("/details/{id}")
    public String details(@PathVariable(value = "id") long id, Model model) {

        Empleado empleado = service.getById(id);
        model.addAttribute("empleado", empleado);
        model.addAttribute("servicio", new Servicio());
        //model.addAttribute("disponibilidad", new Disponibilidad());

        // recorremos el set de disponibilidades del empleado
        for(Disponibilidad disponibilidad : empleado.getDisponibilidades()) {

            // parseamos los datos de String a int para poder operar
            int inicioManiana = Integer.parseInt(disponibilidad.getHoraInicioManiana());
            int finManiana = Integer.parseInt(disponibilidad.getHoraFinManiana());
            int inicioTarde = Integer.parseInt(disponibilidad.getHoraInicioTarde());
            int finTarde = Integer.parseInt(disponibilidad.getHoraFinTarde());

            //caculamos la horas de por la mañana las pasamos a minutos y dividimos en huevos de 10 min

            int huecosmaniana = finManiana - inicioManiana;
            int huecostarde = finTarde - inicioTarde;
            int huecos = huecosmaniana + huecostarde;

            huecos *= 60;
            huecos /= 10;

            // Creamos un array con el numero de huecos para ese dia
            //Array[] totalhHecosDelDia = new Array[huecos];
        }





        return "empleados/details";
    }








}
