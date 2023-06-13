package com.eoi.CitaTe.controllers;

import com.eoi.CitaTe.abstraccomponents.MiControladorGenerico;
import com.eoi.CitaTe.dto.*;
import com.eoi.CitaTe.entities.Empresa;
import com.eoi.CitaTe.entities.Usuario;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

//    @GetMapping("/paginados")
//    public String getAllPaginated(@RequestParam(defaultValue = "1") int page,
//                                  @RequestParam(defaultValue = "10") int size,
//                                  Model model) {
//
//        Pageable pageable = PageRequest.of(page-1, size);
//        Page<Usuario> usuariosPage = usuarioRepository.findAll(pageable);
//
//        model.addAttribute("usuarios", usuariosPage);
//
//        int totalPages = usuariosPage.getTotalPages();
//
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//        return entityName + "/" + "paginas";
//    }


    @GetMapping("/paginados")
    public String paginados(Model model,
                            @RequestParam(defaultValue = "0") int numeroPagina,
                            @RequestParam(defaultValue = "5") int tamanoPagina,
                            @RequestParam(value = "Provincia", required = false) String provincia) {
        this.url = entityName + "/";
        // Creamos un pageable con numero de pagina y tamaño
        Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina);

        // Pasamos todos los empresaDTO como una page
        Page<EmpresaDTO> entitiesPage = empresaMapperService.buscarTodos(pageable);

        // Hacemos una lista de empresaDTO filtrando por
        List<EmpresaDTO> listaFiltradaProvincia = entitiesPage.getContent().stream()
                .filter(empresaDTO -> empresaDTO.getDireccion().getProvincia().equalsIgnoreCase(provincia))
                .collect(Collectors.toList());

//        int totalElements = listaFiltradaProvincia.size(); // Total de elementos en la lista filtrada
//
//        int startIndex = numeroPagina * tamanoPagina;
//        int endIndex = Math.min(startIndex + tamanoPagina, totalElements);
//
//        // Obtiene la sublista de elementos para la página actual
//        List<EmpresaDTO> pageElements = listaFiltradaProvincia.subList(startIndex, endIndex);




        if (provincia != null){

            // no hace ni puto caso al tamanio
            tamanoPagina = 10;
            Pageable pageableProvincia = PageRequest.of(2, 15);



            Page<EmpresaDTO> filteredEntitiesPage = new PageImpl<>(listaFiltradaProvincia, pageableProvincia, listaFiltradaProvincia.size());

            //Pasamos dto al model
            model.addAttribute("entities", filteredEntitiesPage);

            // Verificar si hay una página anterior
            if (filteredEntitiesPage.hasPrevious()) {
                model.addAttribute("paginaAnterior", numeroPagina - 1);
            }
            // Verificar si hay una página siguiente
            if (filteredEntitiesPage.hasNext()) {
                model.addAttribute("siguientePagina", numeroPagina + 1);
            }


        }else {
                    //Pasamos dto al model
                    model.addAttribute("entities", entitiesPage);

                    // Verificar si hay una página anterior
                    if (entitiesPage.hasPrevious()) {
                        model.addAttribute("paginaAnterior", numeroPagina - 1);
                    }
                    // Verificar si hay una página siguiente
                    if (entitiesPage.hasNext()) {
                        model.addAttribute("siguientePagina", numeroPagina + 1);
                    }
                }

        // Agregar pagina de inicio, para utilizar como enlace y poder volver al inicio
        model.addAttribute("Inicio", 0);

        ///////////// PRUEBAS/////////////////////////////////////

        //provincia = "Todos";

        // Para el filtro
        model.addAttribute("provinciaFiltro", provincia);

        return entityName + "/" + "paginas";

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
