package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.ContainerDTO;

import io.fabric8.kubernetes.api.model.Container;

public class ContainerMapper {
    
    private ContainerMapper() {}


    /**
     * Convierte un objeto Container de Kubernetes en un objeto ContainerDTO, que es el que se devuelve
     * en la API de Kate. La información de "ready" se ignora, ya que se obtendrá de ContainerStatus.
     * @param container el objeto Container que se va a convertir
     * @return el objeto ContainerDTO que se ha creado
     */
    public static ContainerDTO toDTO(Container container) {
        ContainerDTO dto = new ContainerDTO();
        dto.setName(container.getName());
        dto.setImage(container.getImage());
        // La información de "ready" se saca de ContainerStatus, no del objeto Container directamente
        dto.setReady(false); 
        return dto;
    }
}

