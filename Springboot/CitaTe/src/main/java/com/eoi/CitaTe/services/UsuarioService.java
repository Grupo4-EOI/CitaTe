package com.eoi.CitaTe.services;

import com.eoi.CitaTe.abstraccomponents.GenericServiceConJPA;
import com.eoi.CitaTe.dto.*;
import com.eoi.CitaTe.entities.*;
import com.eoi.CitaTe.repositories.EmpleadoRepository;
import com.eoi.CitaTe.repositories.EmpresaRepository;
import com.eoi.CitaTe.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericServiceConJPA<Usuario, Long> {

    @Autowired
    UsuarioRepository usuarioRepository;


    @Autowired
    private PasswordEncoder codificadorContraseñas;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    public void CrearCliente(UsuarioDTO usuarioDTO, ClienteDTO clienteDTO){
        Usuario usuario = new Usuario();
        Cliente cliente = new Cliente();

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPass(codificadorContraseñas.encode(usuarioDTO.getPass()));
        usuario.setActivo(true);

        cliente.setNombreCliente(clienteDTO.getNombreCliente());
        cliente.setApellido1Cliente(clienteDTO.getApellido1Cliente());
        cliente.setApellido2Cliente(clienteDTO.getApellido2Cliente());
        cliente.setTelefono(clienteDTO.getTelefono());

        usuario.setCliente(cliente);


        usuarioRepository.save(usuario);
    }



    public void CrearEmpresa(UsuarioDTO usuarioDTO, EmpresaDTO empresaDTO, EmpleadoDTO empleadoDTO, DireccionDTO direccionDTO){

        Cliente cliente = new Cliente();
        Empresa empresa = new Empresa();
        Empleado empleado = new Empleado();
        Usuario usuario = new Usuario();
        Direccion direccion = new Direccion();

        direccion.setCalle(direccionDTO.getCalle());
        direccion.setNumero(direccionDTO.getNumero());
        direccion.setProvincia(direccionDTO.getProvincia());
        direccion.setCiudad(direccionDTO.getCiudad());
        direccion.setCodigoPostal(direccionDTO.getCodigoPostal());



        empresa.setDescripcionEmpresa(empresaDTO.getDescripcionEmpresa());
        empresa.setNombreEmpresa(empresaDTO.getNombreEmpresa());
        empresa.setCif(empresaDTO.getCif());
        empresa.setLogoEmpresa(empresaDTO.getLogoEmpresa());
        empresa.setTipoNegocio(empresaDTO.getTipoNegocio());

        empresa.setDireccion(direccion);



//      empresa.setContacto(empresaDTO.getContacto());

        empresaRepository.save(empresa);

        empleado.setNombreEmpleado(empleadoDTO.getNombreEmpleado());
        empleado.setApellido1Empleado(empleadoDTO.getApellido1Empleado());
        empleado.setApellido2Empleado(empleadoDTO.getApellido2Empleado());

        empleado.setEmpresa(empresa);

        empleadoRepository.save(empleado);


        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPass(codificadorContraseñas.encode(usuarioDTO.getPass()));
        usuario.setActivo(true);

        usuario.setEmpleado(empleado);
        //usuario.setRol(jefe)

        // Configurar mas adelante rol de jefe para este usuario.

        usuarioRepository.save(usuario);

      //  private Set<Empleado> empleados = new HashSet<>();
      //  private CatalogoDeServicio catalogoDeServicio;

        //private Disponibilidad disponibilidad;
        //private Set<Servicio> servicios = new HashSet<>();

    }

    // Hecho el registro de la empresa como "Propietario" añadimos este método para poder añadir empleados.
    public void CrearEmpleado(UsuarioDTO usuarioDTO, EmpleadoDTO empleadoDTO){

        Empleado empleado = new Empleado();
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPass(codificadorContraseñas.encode(usuarioDTO.getPass()));
        usuario.setActivo(true);

        empleado.setNombreEmpleado(empleadoDTO.getNombreEmpleado());
        empleado.setApellido1Empleado(empleadoDTO.getApellido1Empleado());
        empleado.setApellido2Empleado(empleadoDTO.getApellido2Empleado());

        //empleado.setEmpresa(empleadoDTO.getEmpresa());  PREGUNTAR en tutorio

        usuario.setEmpleado(empleado);
        //usuario.setRol(empleado)

        usuarioRepository.save(usuario);




    }
}
