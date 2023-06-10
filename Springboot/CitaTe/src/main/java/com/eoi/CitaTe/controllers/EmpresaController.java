package com.eoi.CitaTe.controllers;

import com.eoi.CitaTe.abstraccomponents.MiControladorGenerico;
import com.eoi.CitaTe.dto.*;
import com.eoi.CitaTe.entities.Empresa;
import com.eoi.CitaTe.repositories.EmpresaRepository;
import com.eoi.CitaTe.repositories.UsuarioRepository;
import com.eoi.CitaTe.services.EmpleadoService;
import com.eoi.CitaTe.services.EmpresaMapperService;
import com.eoi.CitaTe.services.EmpresaService;
import com.eoi.CitaTe.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("${url.empresa}")
@RequiredArgsConstructor
public class EmpresaController extends MiControladorGenerico<Empresa> {

    @Value("${url.empresa}")
    private String urlBase;

    private String url = "empresas";
    private String entityName = "empresa";

    private final EmpresaRepository empresaRepository;

    @PostConstruct
    private void init() {
        super.entityName = entityName;
        super.url = url;
    }

    @Autowired
    EmpresaMapperService empresaMapperService;


    @GetMapping("/all2")
    public String getAllentities(Model model) {
        this.url = entityName + "/";
        List<Empresa> entities = service.listAll();
        model.addAttribute("entities", entities);
        //    model.addAttribute("nombreVista", entityName);
        return url + "all-entities2"; // Nombre de la plantilla para mostrar todas las entidades
    }

    @Override
    @GetMapping("/all")
    public String getAll(Model model) {
        this.url = entityName + "/";

// O bien mostramos todas todos los elementos como entidades
        // List<Valoracion> entities = service.listAll();

// o tras mucho trabajo tambien podemos mostrar  como dto
        List<EmpresaDTO> entities = empresaMapperService.buscarTodos();

        model.addAttribute("entities", entities);
        return url + "all-entities"; // Nombre de la plantilla para mostrar todas las entidades
    }




    //Enviar los datos al registro de empresa

    @GetMapping("/createEmpresa")
    public String create(Model model) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("empresaDTO", empresaDTO);
        model.addAttribute("empleadoDTO", empleadoDTO);
//        model.addAttribute("url", url);
        model.addAttribute("entityName", entityName);

        return "registroEmpresa/registroEmpresa2"; // Nombre de la plantilla para mostrar todas las entidades
    }


    @PostMapping("/createEmpresa3")
    public String create1(Model model,
                          @ModelAttribute EmpresaDTO empresaDTO,
                          @ModelAttribute UsuarioDTO usuarioDTO,
                          @ModelAttribute EmpleadoDTO empleadoDTO){

        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("empresaDTO", empresaDTO);
        model.addAttribute("empleadoDTO", empleadoDTO);
//        model.addAttribute("url", url);
        model.addAttribute("entityName", entityName);


        return "registroEmpresa/registroEmpresa3"; // Nombre de la plantilla para mostrar todas las entidades
    }

    @PostMapping("/createEmpresa4")
    public String create2(Model model,
                          @ModelAttribute EmpresaDTO empresaDTO,
                          @ModelAttribute UsuarioDTO usuarioDTO,
                          @ModelAttribute EmpleadoDTO empleadoDTO){

        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("empresaDTO", empresaDTO);
        model.addAttribute("empleadoDTO", empleadoDTO);
//        model.addAttribute("url", url);
        model.addAttribute("entityName", entityName);

        return "registroEmpresa/registroEmpresa4"; // Nombre de la plantilla para mostrar todas las entidades
    }


    @PostMapping("/createEmpresa5")
    public String create3(Model model,
                          @ModelAttribute EmpresaDTO empresaDTO,
                          @ModelAttribute UsuarioDTO usuarioDTO,
                          @ModelAttribute EmpleadoDTO empleadoDTO,
                          @ModelAttribute DireccionDTO direccionDTO){

        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("empresaDTO", empresaDTO);
        model.addAttribute("empleadoDTO", empleadoDTO);
        model.addAttribute("direccionDTO", direccionDTO);
//        model.addAttribute("url", url);
        model.addAttribute("entityName", entityName);

        return "registroEmpresa/registroEmpresa5"; // Nombre de la plantilla para mostrar todas las entidades
    }







    //// alta//////////////////////////////////////

    private final UsuarioService usuarioService;
    private final EmpresaService empresaService;

    @PostMapping(value = {"/altaEmpresa"})
    public String update(@ModelAttribute UsuarioDTO usuarioDTO,
                         @ModelAttribute EmpresaDTO empresaDTO,
                         @ModelAttribute EmpleadoDTO empleadoDTO,
                         @ModelAttribute DireccionDTO direccionDTO) {

        usuarioService.CrearEmpresa(usuarioDTO, empresaDTO, empleadoDTO, direccionDTO);

        return "/registroEmpresa/registroEmpresa6";
    }





}
