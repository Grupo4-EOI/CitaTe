package com.eoi.CitaTe.services.mapper;

import com.eoi.CitaTe.dto.ClienteDTONuevo;
import com.eoi.CitaTe.entities.Cliente;
import com.eoi.CitaTe.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {
    @Autowired
    private ClienteRepository clienteRepository;
    public ClienteDTONuevo toDto(Cliente entidad){
        final ClienteDTONuevo dto = new ClienteDTONuevo();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }
    //Convertir de dto a entidad
    public Cliente toEntity(ClienteDTONuevo dto){
        final Cliente entidad = new Cliente();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto,entidad);

        return entidad;
    }
}
