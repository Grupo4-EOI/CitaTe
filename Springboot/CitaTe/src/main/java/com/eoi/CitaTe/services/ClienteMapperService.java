package com.eoi.CitaTe.services;

import com.eoi.CitaTe.dto.AltaGenericaDto;
import com.eoi.CitaTe.dto.ClienteDTO;
import com.eoi.CitaTe.dto.DisponibilidadDTO;
import com.eoi.CitaTe.entities.Cliente;
import com.eoi.CitaTe.entities.Disponibilidad;
import com.eoi.CitaTe.repositories.ClienteRepository;
import com.eoi.CitaTe.repositories.DisponibilidadRepository;
import com.eoi.CitaTe.services.mapper.ClienteMapper;
import com.eoi.CitaTe.services.mapper.DisponibilidadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapperService extends AbstractBusinessService<Cliente, Long, ClienteDTO, ClienteRepository, ClienteMapper>{

    public ClienteMapperService(ClienteRepository clienteRepository, ClienteMapper mapper) {
        super(clienteRepository, mapper);
    }

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente CrearCliente(AltaGenericaDto altaGenericaDto) {

        Cliente cliente = new Cliente();
        cliente.setNombreCliente(altaGenericaDto.getCliente().getNombreCliente());
        cliente.setApellido1Cliente(altaGenericaDto.getCliente().getApellido1Cliente());
        cliente.setApellido2Cliente(altaGenericaDto.getCliente().getApellido2Cliente());
        cliente.setTelefono(altaGenericaDto.getCliente().getTelefono());

        return clienteRepository.save(cliente);

    }

}
