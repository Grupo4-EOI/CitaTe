package com.eoi.CitaTe.security.service;

import com.eoi.CitaTe.entities.Rol;
import com.eoi.CitaTe.entities.Usuario;
import com.eoi.CitaTe.repositories.UsuarioRepository;
import com.eoi.CitaTe.security.details.MiUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MiUserDetailService  implements UserDetailsService {


    // Instanaciamos el repositorio creado para usuario y lo dejamos autowired para
    // que spring lo gestione
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // llamamos a los detaller de usuario que anteriormente hemos creado

        MiUserDetails userDetails = new MiUserDetails();

        Optional<Usuario> usuarioObtenidoDeLaBD = usuarioRepository.findByEmail(email);

        if (usuarioObtenidoDeLaBD.isPresent()){
            userDetails.setUsername(usuarioObtenidoDeLaBD.get().getEmail());
            userDetails.setPassword(usuarioObtenidoDeLaBD.get().getPass());
            userDetails.setId(usuarioObtenidoDeLaBD.get().getId());

            // Para establecer el nombre del usuario dependera de si es cliente o empleado por lo que configuramos if else

            if(usuarioObtenidoDeLaBD.get().getEmpleado()==null){

                userDetails.setNombreUsuario(usuarioObtenidoDeLaBD.get().getCliente().getNombreCliente());
                userDetails.setApellidoUsuario(usuarioObtenidoDeLaBD.get().getCliente().getApellido1Cliente());
            }else {
                userDetails.setNombreUsuario(usuarioObtenidoDeLaBD.get().getEmpleado().getNombreEmpleado());
                userDetails.setApellidoUsuario(usuarioObtenidoDeLaBD.get().getEmpleado().getApellido1Empleado());
            }




            // faltaria incluir si es empleado o si es  con setGrantedAuthorities(getAuthorities(usuarioObtenidoDeLaBD.get().getRoles()));

            return  userDetails;
        }

        return null;
    }

    ////////// cosas que no se lo que son



}
