package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.ServiceDTO;

import io.fabric8.kubernetes.api.model.Service;

public class ServiceMapper {
    public static ServiceDTO toDTO(Service service) {
        ServiceDTO dto = new ServiceDTO();
        dto.setName(service.getMetadata().getName());
        dto.setNamespace(service.getMetadata().getNamespace());
        dto.setType(service.getSpec().getType());
        dto.setClusterIP(service.getSpec().getClusterIP());
        dto.setPorts(service.getSpec().getPorts()
            .stream().map(PortMapper::toDTO).toList());
        dto.setSelector(service.getSpec().getSelector());
        return dto;
    }
}

