package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.PortDTO;

import io.fabric8.kubernetes.api.model.ServicePort;

public class PortMapper {
    public static PortDTO toDTO(ServicePort port) {
        PortDTO dto = new PortDTO();
        dto.setPort(port.getPort());
        dto.setTargetPort(port.getTargetPort().getIntVal()); // puede ser string o int
        return dto;
    }
}

