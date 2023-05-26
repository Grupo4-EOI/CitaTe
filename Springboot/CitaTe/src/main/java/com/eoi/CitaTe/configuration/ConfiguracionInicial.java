package com.eoi.CitaTe.configuration;

import com.eoi.CitaTe.entities.Usuario;
import com.eoi.CitaTe.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;



@Component
public class ConfiguracionInicial implements ApplicationListener<ContextRefreshedEvent> {


    boolean configuracionRealizada = false;

    @Autowired
    private UsuarioRepository usuarioRepository;







        @Override
        @Profile("local")
        @Transactional
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if (configuracionRealizada)
                return;

            Usuario usuario = new Usuario();
            usuario.setEmail("cliente@citate.com");
            usuario.setActivo(true);
            usuario.setPass("puto");


            usuarioRepository.save(usuario);


            Usuario usuario2 = new Usuario();

            usuario2.setEmail("empleado@citate.com");
            usuario2.setActivo(true);
            usuario2.setPass("puto");
            usuarioRepository.save(usuario2);


            configuracionRealizada = true;
        }




}

