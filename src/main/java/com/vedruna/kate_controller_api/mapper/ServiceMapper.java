package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.ServiceDTO;

import io.fabric8.kubernetes.api.model.Service;

public class ServiceMapper {
    /**
     * Convierte un objeto Service de Kubernetes en un objeto ServiceDTO, que es el que se devuelve
     * en la API de Kate.
     * @param service el objeto Service que se va a convertir
     * @return el objeto ServiceDTO que se ha creado
     */
    public static ServiceDTO toDTO(Service service) {
        ServiceDTO dto = new ServiceDTO();
        dto.setName(service.getMetadata().getName());
        dto.setNamespace(service.getMetadata().getNamespace());
        dto.setType(service.getSpec().getType());
        dto.setClusterIP(service.getSpec().getClusterIP());
        // se hace el stream para sacar la información de los puertos
        dto.setPorts(service.getSpec().getPorts()
            .stream().map(PortMapper::toDTO).toList());
        dto.setSelector(service.getSpec().getSelector());
        return dto;
    }
}

