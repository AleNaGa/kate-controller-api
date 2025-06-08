package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.PortDTO;

import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.ServicePort;

public class PortMapper {
    /**
     * Convierte un objeto ServicePort de Kubernetes en un objeto PortDTO, que es el que se devuelve
     * en la API de Kate.
     * @param port el objeto ServicePort que se va a convertir
     * @return el objeto PortDTO que se ha creado
     */
    public static PortDTO toDTO(ServicePort port) {
       PortDTO dto = new PortDTO();
        dto.setPort(port.getPort());

        IntOrString target = port.getTargetPort();
        if (target != null && target.getIntVal() != null) {
            dto.setTargetPort(target.getIntVal());
        } else {
            dto.setTargetPort(-1); 
        }

        return dto;
    }
}

