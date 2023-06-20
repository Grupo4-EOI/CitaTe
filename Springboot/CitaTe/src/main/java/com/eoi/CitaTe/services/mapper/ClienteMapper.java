package com.eoi.CitaTe.services.mapper;

import com.eoi.CitaTe.dto.ClienteDTO;
import com.eoi.CitaTe.entities.Cliente;
import com.eoi.CitaTe.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper extends AbstractServiceMapper<Cliente, ClienteDTO>{

    public AltaGenericaDto toDto(Cliente entidad){
        final AltaGenericaDto dto = new AltaGenericaDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    public Cliente toEntity(AltaGenericaDto dto){
        final Cliente entidad = new Cliente();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto,entidad);
       // long id =  dto.getId();
        //Buscamos las reservas
       // entidad.setReservas(clienteRepository.findById(id).get().getReservas());
        return entidad;
    }
    public ClienteMapper() {
    }
}
