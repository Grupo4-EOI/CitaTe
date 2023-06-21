package com.eoi.CitaTe.services;

import com.eoi.CitaTe.abstraccomponents.GenericServiceConJPA;
import com.eoi.CitaTe.entities.Rol;
import com.eoi.CitaTe.repositories.FacturacionRepository;
import com.eoi.CitaTe.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService extends GenericServiceConJPA<Rol, Long> {

    @Autowired
    RolRepository rolRepository;

}
