package com.eoi.CitaTe.services.mapper;

import com.eoi.CitaTe.dto.ClienteDTONuevo;
import com.eoi.CitaTe.dto.EmpleadoDTONuevo;
import com.eoi.CitaTe.dto.UsuarioDTO;
import com.eoi.CitaTe.dto.UsuarioDTO1;
import com.eoi.CitaTe.entities.Cliente;
import com.eoi.CitaTe.entities.Empleado;
import com.eoi.CitaTe.entities.Usuario;
import com.eoi.CitaTe.repositories.ClienteRepository;
import com.eoi.CitaTe.repositories.EmpleadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper {
    @Autowired
    private PasswordEncoder codificadorContraseñas;
    @Autowired
    ClienteMapper clienteMapper;
    @Autowired
    EmpleadoMapper empleadoMapper;

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;

    //Ofrece los método para copiar datos desde dtos a entidades para el guardado
    public Usuario toEntity(UsuarioDTO1 usrdto){
        final Usuario entidad = new Usuario();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,usrdto);
        String strCliente = "cliente";
        System.out.println(usrdto.getTipoAlta().equals(strCliente));
        if (usrdto.getTipoAlta().equals(strCliente)){
            //Contraseña
            //entidad.setPass(codificadorContraseñas.encode(usrdto.getPass()));
            entidad.setPass(usrdto.getPass());
            //Pasamos a la parte de clientes
            //Mapeamos del dto a ala entidad
            Cliente clienteaguardar = clienteMapper.toEntity(usrdto.getClienteDTONuevo());
            //Guardamos la entidad cliente
            Cliente clienteguardado = clienteRepository.save(clienteaguardar);
            //Añadimos la entidad cliente al usuario
            entidad.setCliente(clienteguardado);
        } else {
            //Pasamos a la parte de empresas
            //Mapeamos del dto a a la entidad
            Empleado empleadoaguardar = empleadoMapper.toEntity(usrdto.getEmpleadoDTONuevo());
            //Para el id de empresa leer y añadir el elemento
            //            usrdto.getEmpleadoDTONuevo().getEmpresa_id()
            //  Busco la empresa completa
            //empleadoaguardar.setEmpresa(...);


            //Guardamos la entidad cliente
            Empleado empleadoguardado = empleadoRepository.save(empleadoaguardar);
            //Para el id de empresa leer y añadir el elemento
            //            usrdto.getEmpleadoDTONuevo().getEmpresa_id()


            //Añadimos la entidad cliente al usuario
            entidad.setEmpleado(empleadoguardado);
        }
        return entidad;
    }
    public UsuarioDTO toDtoUsuario(Usuario entidad){
        final UsuarioDTO dto = new UsuarioDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto,entidad);
        return dto;
    }
    public ClienteDTONuevo toDtoCliente(Usuario entidad){
        final ClienteDTONuevo dto = new ClienteDTONuevo();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto,entidad.getCliente());
        return dto;
    }
    public EmpleadoDTONuevo toDtoEmpleado(Usuario entidad){
        final EmpleadoDTONuevo dto = new EmpleadoDTONuevo();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto,entidad.getEmpleado());
        return dto;
    }

}
