package com.eoi.CitaTe.controllers;


import com.eoi.CitaTe.abstraccomponents.MiControladorGenerico;
import com.eoi.CitaTe.dto.ClienteDTO;
import com.eoi.CitaTe.dto.UsuarioDTO;
import com.eoi.CitaTe.entities.Usuario;
import com.eoi.CitaTe.repositories.UsuarioRepository;
import com.eoi.CitaTe.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Value("${url.usuario}")
    private String urlBase;


    private String url = "usuarios";
    private String entityName = "usuario";
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

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



    @Override
    @GetMapping("/create")
    public String create(Model model) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        ClienteDTO clienteDTO = new ClienteDTO();
        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("clienteDTO", clienteDTO);
//        model.addAttribute("url", url);
        model.addAttribute("entityName", entityName);

        return "usuarios/altaUsuario"; // Nombre de la plantilla para mostrar todas las entidades
    }

    @PostMapping(value = {"/alta"})
    public String update(@ModelAttribute UsuarioDTO usuarioDTO,
                         @ModelAttribute ClienteDTO clienteDTO) {
        usuarioService.CrearCliente(usuarioDTO, clienteDTO);


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

