package com.eoi.CitaTe.controllers;


import com.eoi.CitaTe.abstraccomponents.MiControladorGenerico;
import com.eoi.CitaTe.dto.AltaGenericaDto;
import com.eoi.CitaTe.dto.UsuarioDTO1;
import com.eoi.CitaTe.entities.*;
import com.eoi.CitaTe.repositories.UsuarioRepository;
import com.eoi.CitaTe.services.DisponibilidadService;
import com.eoi.CitaTe.services.EmpresaService;
import com.eoi.CitaTe.services.ServicioService;
import com.eoi.CitaTe.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad Usuario.
 *
 * <p>Esta clase se encarga de manejar las solicitudes relacionadas con la entidad Usuario utilizando la funcionalidad proporcionada por
 * la clase {@link MiControladorGenerico}.</p>
 *
 * <p>Los principales componentes de esta clase son:</p>
 * <ul>
 *     <li>Inversión de control (IoC): La clase extiende MiControladorGenerico y utiliza la funcionalidad proporcionada por ella.
 *     Esto permite que los detalles de implementación sean proporcionados por la clase genérica y facilita la reutilización de código
 *     y la consistencia en la implementación de controladores.</li>
 *     <li>Inyección de dependencias (DI): La clase utiliza inyección de dependencias para obtener el nombre de la entidad gestionada.
 *     El valor de entityName se inyecta utilizando la anotación @Value en la propiedad correspondiente. Esto permite la separación de
 *     responsabilidades y mejora la mantenibilidad y escalabilidad del controlador.</li>
 *     <li>Principio de abstracción: La clase extiende la clase genérica MiControladorGenerico, lo que permite una implementación
 *     común de las operaciones CRUD para la entidad Usuario. Esto facilita la reutilización de código y mejora la consistencia en
 *     la implementación de controladores.</li>
 * </ul>
 *
 * @param <Usuario> El tipo de entidad gestionada por el controlador.
 * @Author Alejandro Teixeira Muñoz
 */
@Controller
@RequestMapping("${url.usuario}")
@RequiredArgsConstructor
public class UsuarioController extends MiControladorGenerico<Usuario> {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    DisponibilidadService disponibilidadService;

    @Autowired
    ServicioService servicioService;

    @Value("${url.usuario}")
    private String urlBase;


    private String url = "usuarios";
    private String entityName = "usuario";
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor de la clase UsuarioController.
     * Se utiliza para crear una instancia del controlador.
     */


    /**
     * Método de inicialización para establecer el valor de entityName y entityPrefix.
     * El valor de entityName se obtiene de una propiedad configurada en un archivo de propiedades utilizando la anotación @Value.
     * Después de la construcción del objeto UsuarioController, se llama a este método para establecer los valores necesarios.
     *
     * @PostConstruct indica que este método debe ejecutarse después de que se haya construido el objeto UsuarioController.
     * Es una anotación de JSR-250 y se utiliza para realizar tareas de inicialización una vez que todas las dependencias hayan sido inyectadas.
     * En este caso, se utiliza para asegurar que entityName y entityPrefix se establezcan correctamente después de la construcción del objeto.
     * @Author Alejandro Teixeira Muñoz
     */


    @GetMapping("/create/nuevaalta")
    public String createEmp(Model model) {
        AltaGenericaDto  altaGenericaDto = new AltaGenericaDto();
        model.addAttribute("datos",altaGenericaDto);
        return "registroEmpresa/nuevaalta";
    }

    @GetMapping("/create/cliente")
    public String createCl(Model model) {
        UsuarioDTO1 usuarioDTO1 = new UsuarioDTO1();
        List<Empresa> empresaList = empresaService.listAll();
        List<Disponibilidad> disponibilidadList = disponibilidadService.listAll();
        List<Servicio> servicioList = servicioService.listAll();

        usuarioDTO1.setTipoAlta("cliente");
        model.addAttribute("entity", usuarioDTO1);
        model.addAttribute("empresas",empresaList);
        model.addAttribute("disponibilidades",disponibilidadList);
        model.addAttribute("servicios",servicioList);
        //        model.addAttribute("url", url);
        model.addAttribute("entityName", entityName);

        return "usuarios/altaUsuario"; // Nombre de la plantilla para mostrar todas las entidades
    }

    @GetMapping("/create/empleado")
    public String createEmpl(Model model) {
        UsuarioDTO1 usuarioDTO1 = new UsuarioDTO1();
        usuarioDTO1.setTipoAlta("empleado");
        model.addAttribute("entity", usuarioDTO1);
//        model.addAttribute("url", url);
        model.addAttribute("entityName", entityName);

        return "usuarios/altaUsuario"; // Nombre de la plantilla para mostrar todas las entidades
    }

    @PostMapping(value = {"/create"})
    public String update(@ModelAttribute UsuarioDTO1 usuarioDTO) {
        usuarioService.CrearUsuario(usuarioDTO);

        return "registroEmpresa/registroEmpresa12";

    }


    // controlador para devolver los usuario paginados


    @GetMapping("/paginados")
    public String obtenerUsuariosPaginados(
            @RequestParam(defaultValue = "0") int numeroPagina,
            @RequestParam(defaultValue = "10") int tamanoPagina,
            Model model) {
        Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina);
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);
        model.addAttribute("usuarios", usuarioPage);
        return "usuarios/usuariosPaginados";
    }

}

