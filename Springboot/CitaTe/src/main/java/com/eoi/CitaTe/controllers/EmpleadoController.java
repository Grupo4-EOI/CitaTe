package com.eoi.CitaTe.controllers;

import com.eoi.CitaTe.abstraccomponents.MiControladorGenerico;
import com.eoi.CitaTe.calendario.DiaDelCalendario;
import com.eoi.CitaTe.calendario.Evento;
import com.eoi.CitaTe.dto.ClienteDTO;
import com.eoi.CitaTe.dto.EmpleadoDTO;
import com.eoi.CitaTe.dto.UsuarioDTO;
import com.eoi.CitaTe.dto.ValoracionDTO;
import com.eoi.CitaTe.entities.*;
import com.eoi.CitaTe.errorcontrol.exceptions.MiEntidadNoEncontradaException;
import com.eoi.CitaTe.services.DisponibilidadMapperService;
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
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    DisponibilidadMapperService disponibilidadMapperService;

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
//    @GetMapping("/details/{id}")
//    public String details(@PathVariable(value = "id") long id, Model model) {
//
//        Empleado empleado = service.getById(id);
//        model.addAttribute("empleado", empleado);
//        model.addAttribute("servicio", new Servicio());
//        //model.addAttribute("disponibilidad", new Disponibilidad());
//
//        // recorremos el set de disponibilidades del empleado
//        for(Disponibilidad disponibilidad : empleado.getDisponibilidades()) {
//
//            // parseamos los datos de String a int para poder operar
//            int inicioManiana = Integer.parseInt(disponibilidad.getHoraInicioManiana());
//            int finManiana = Integer.parseInt(disponibilidad.getHoraFinManiana());
//            int inicioTarde = Integer.parseInt(disponibilidad.getHoraInicioTarde());
//            int finTarde = Integer.parseInt(disponibilidad.getHoraFinTarde());
//
//            //caculamos la horas de por la mañana las pasamos a minutos y dividimos en huevos de 10 min
//
//            int huecosmaniana = finManiana - inicioManiana;
//            int huecostarde = finTarde - inicioTarde;
//            int huecos = huecosmaniana + huecostarde;
//
//            huecos *= 60;
//            huecos /= 10;
//
//            // Creamos un array con el numero de huecos para ese dia
//            //Array[] totalhHecosDelDia = new Array[huecos];
//        }
//        return "empleados/details";
//    }


    /// con calendario

    @GetMapping("/details/{id}")
    public String details(@PathVariable(value = "id") long id,
                          @RequestParam("year") Optional<Integer> yearOK,
                          @RequestParam("month") Optional<Integer> monthOK,
                          @RequestParam("day") Optional<Integer> dayOK,
                          @RequestParam(value = "diasLaborables", required = false) List<Integer> diasLaborables,
                          Model model,
                          Principal principal) {

        Empleado empleado = service.getById(id);
        model.addAttribute("empleado", empleado);
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("disponibilidad", new Disponibilidad());


        ///////// Calendario ////////////////////////////

        LocalDate fechaActual = LocalDate.now();
        //Recojo los valores de los parametros, o pongo los de la fecha actual por defecto si no vienen rellenos
        Integer numeroColumnas = 7;
        Integer year = yearOK.orElse(fechaActual.getYear());
        Integer month = monthOK.orElse(fechaActual.getMonthValue());
        Integer dayOfMonth = dayOK.orElse(1);

        //Si nos han dado un mes 13, sumamos uno al año y ponemos mes 1
        if(month == 13)
        {
            year+=1;
            month=1;
        }
        if(month==0)
        {
            year-=1;
            month=12;
        }

        //Primero calculo un objeto de fecha de la fecha que obtengo de las variables
        LocalDate fechaCalendario = LocalDate.of(year,month, dayOfMonth);
        //Creo una lista multidimensional / anidada de Strings para tener los dias de la semana
        List<List<DiaDelCalendario>> mesCompleto = new ArrayList<>();
        List<DiaDelCalendario> semana = new ArrayList<>();

        //Creo una variable para saber en qué semana estoy, ya que la primera semana es diferente
        int semanaActual=0;

        //Este bucle se ejecuta 1 vez por dia del mes (28...31...)
        for (int i=1; i<=fechaCalendario.lengthOfMonth(); i++ )
        {
            //Calculo la fecha que está ahora mismo recorriendo el generador de calendarios
            LocalDate fechaEnUso = LocalDate.of(year,month,i);

            // Comprobamos sobre los dias de disponibilidades si el empleado trabaja o no.-------
            // al objeto dia d

            //Si la fecha en uso es Lunes (ordinal==0) o la semana actual es la semana 0 debo
            // crear un nuevo array de semana (lo que viene a ser empezar la semana)
            if(fechaEnUso.getDayOfWeek().ordinal() == 0 || semanaActual == 0)
            {
                //Creo mi array de semana
                semana = new ArrayList<>();
                //Añado la nueva semana al mes
                mesCompleto.add(semana);


                //SI es la primera semana del mes, puedo necesitar añadir huecos en blanco
                if(semanaActual == 0)
                {
                    //Al ser la primera semana del mes,
                    // desde el día 0 (que seria la primera casilla lunes) hasta el día de la semana del día 1 del mes que estamos viendo
                    // Añado casillas en blanco para los días que hay que dejar en blanco
                    for (int j=0;j<fechaEnUso.getDayOfWeek().ordinal(); j++)
                    {
                        DiaDelCalendario diaDelCalendario = new DiaDelCalendario();
                        diaDelCalendario.setFecha(fechaEnUso);
                        diaDelCalendario.setDiaNulo(true);
                        // si j esta dentro de la lista marcamos el campo trabajado a 1 si no se queda a 0
                        mesCompleto.get(semanaActual).add(diaDelCalendario);
                    }
                }
                //Actualizo el numero de la semana una vez he rellenado lo necesario en la primera
                semanaActual+=1;
            }
            DiaDelCalendario diaDelCalendario = new DiaDelCalendario();
            diaDelCalendario.setFecha(fechaEnUso);

            //Yo aqui creo una lista de eventos, siempre la misma para todos los dias.
            //Aqui cada uno deberia rellenar la lista de eventos del dia segun considere.
            // En el caso de tener el usuario y quere utilizar el principal ,por ejemplo hariamos.
            //User usuario = UserRepository.findByUsername(principal.getName())

//            List<Evento> usuario.getEventos();


            List<Evento> eventos = new ArrayList<>();
            Evento evento1 = new Evento();
            evento1.setFechaInicio(LocalDateTime.of(2023, 5,29,15,50));
            evento1.setNombre("Disponibilidad");
            eventos.add(evento1);


//            diaDelCalendario.setEventos(eventos);
            semana.add(diaDelCalendario);

        }

        model.addAttribute("year", year);
        model.addAttribute("month", month);

        //Paso al html el objeto dias
        model.addAttribute("mesCompleto", mesCompleto);

        //Dias laborables

        //Necesiso un método busque la disponibilidad del empleado


        List<Disponibilidad> disponibilidades = disponibilidadMapperService.getRepo().findDisponibilidadByEmpleado_Id(id);




//        for(Disponibilidad disponibilidad : empleado.getDisponibilidades()) {
//
//            diasLaborables.add(disponibilidad.getDiaDeLaSemana());
//        }
        int diasintrabajo = 1;
        model.addAttribute("diasintrabajo", diasintrabajo);


        return "empleados/details";
    }







}
