package com.eoi.CitaTe.services;

import com.eoi.CitaTe.abstraccomponents.GenericServiceConJPA;
import com.eoi.CitaTe.dto.UsuarioDTO1;
import com.eoi.CitaTe.entities.Usuario;
import com.eoi.CitaTe.repositories.UsuarioRepository;
import com.eoi.CitaTe.services.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericServiceConJPA<Usuario, Long> {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private  UsuarioMapper usuarioMapper;


    @Autowired
    private PasswordEncoder codificadorContraseñas;
    public void CrearUsuario(UsuarioDTO1 usuarioDTO ){
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuarioRepository.save(usuario);
    }
}
