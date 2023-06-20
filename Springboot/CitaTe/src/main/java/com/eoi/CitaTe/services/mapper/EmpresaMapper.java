package com.eoi.CitaTe.services.mapper;


import com.eoi.CitaTe.dto.AltaGenericaDto;
import com.eoi.CitaTe.dto.EmpresaDTO;
import com.eoi.CitaTe.dto.ValoracionDTO;
import com.eoi.CitaTe.entities.Empresa;
import com.eoi.CitaTe.entities.Valoracion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMapper extends AbstractServiceMapper <Empresa, AltaGenericaDto>{


    // Convertir de entidad a dto

    @Override
    public AltaGenericaDto toDto(Empresa entidad){
        final AltaGenericaDto dto = new AltaGenericaDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }
    //Convertir de dto a entidad
    @Override
    public Empresa toEntity(AltaGenericaDto dto){
        final Empresa entidad = new Empresa();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto,entidad);
        return entidad;
    }

    public EmpresaMapper() {
    }
}
