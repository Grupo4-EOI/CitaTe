package com.eoi.CitaTe.services;

import com.eoi.CitaTe.dto.AltaGenericaDto;
import com.eoi.CitaTe.dto.EmpleadoDTO;
import com.eoi.CitaTe.dto.ValoracionDTO;
import com.eoi.CitaTe.entities.Empleado;
import com.eoi.CitaTe.repositories.EmpleadoRepository;
import com.eoi.CitaTe.services.mapper.EmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoMapperService extends AbstractBusinessService<Empleado, Long, AltaGenericaDto, EmpleadoRepository, EmpleadoMapper> {
        public EmpleadoMapperService(EmpleadoRepository repo, EmpleadoMapper mapper) {
                super(repo, mapper);
        }

        @Autowired
        EmpleadoRepository empleadoRepository;

        public Empleado CrearEmpleado (AltaGenericaDto altaGenericaDto ){

                Empleado empleado = new Empleado();

                empleado.setNombreEmpleado(altaGenericaDto.getEmpleado().getNombreEmpleado());
                empleado.setApellido1Empleado(altaGenericaDto.getEmpleado().getApellido1Empleado());
                empleado.setApellido2Empleado(altaGenericaDto.getEmpleado().getApellido2Empleado());


                //empleado.setEmpresa();
                //empleado.setServicios();
                //empleado.setDisponibilidad();

                return empleadoRepository.save(empleado);


        }


}
