package com.eoi.CitaTe.services;


import com.eoi.CitaTe.dto.*;
import com.eoi.CitaTe.entities.*;
import com.eoi.CitaTe.repositories.EmpresaRepository;
import com.eoi.CitaTe.repositories.ValoracionRepository;
import com.eoi.CitaTe.services.mapper.EmpresaMapper;
import com.eoi.CitaTe.services.mapper.ValoracionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class EmpresaMapperService extends AbstractBusinessService<Empresa, Long, EmpresaDTO, EmpresaRepository, EmpresaMapper> {

    public EmpresaMapperService(EmpresaRepository repo, EmpresaMapper serviceMapper,
                                EmpresaRepository empresaRepository) {
        super(repo, serviceMapper);
        this.empresaRepository = empresaRepository;
    }

    public Empresa CrearEmpresa(AltaGenericaDto altaGenericaDto){


        Empresa empresa = new Empresa();


        empresa.setDescripcionEmpresa(altaGenericaDto.getEmpresa().getDescripcionEmpresa());
        empresa.setNombreEmpresa(altaGenericaDto.getEmpresa().getNombreEmpresa());
        empresa.setCif(altaGenericaDto.getEmpresa().getCif());
        empresa.setLogoEmpresa(altaGenericaDto.getEmpresa().getLogoEmpresa());
        empresa.setTipoNegocio(altaGenericaDto.getEmpresa().getTipoNegocio());


        return empresaRepository.save(empresa);
    }


}
