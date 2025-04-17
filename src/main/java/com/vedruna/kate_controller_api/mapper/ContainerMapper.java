package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.ContainerDTO;

import io.fabric8.kubernetes.api.model.Container;

public class ContainerMapper {
    public static ContainerDTO toDTO(Container container) {
        ContainerDTO dto = new ContainerDTO();
        dto.setName(container.getName());
        dto.setImage(container.getImage());
        // La información de "ready" se saca de ContainerStatus, no del objeto Container directamente
        dto.setReady(false); 
        return dto;
    }
}

