package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.DeploymentDTO;

import io.fabric8.kubernetes.api.model.apps.Deployment;

public class DeploymentMapper {
    /**
     * Convierte un objeto Deployment de Kubernetes en un objeto DeploymentDTO, que es el que se devuelve
     * en la API de Kate.
     * @param deployment el objeto Deployment que se va a convertir
     * @return el objeto DeploymentDTO que se ha creado
     */
    public static DeploymentDTO toDTO(Deployment deployment) {
        DeploymentDTO dto = new DeploymentDTO();
        dto.setName(deployment.getMetadata().getName());
        dto.setNamespace(deployment.getMetadata().getNamespace());
        dto.setReplicas(deployment.getSpec().getReplicas());
        dto.setAvailableReplicas(
            // La información de "availableReplicas" se saca de DeploymentStatus, no del objeto Deployment directamente
            deployment.getStatus().getAvailableReplicas() != null ?
            deployment.getStatus().getAvailableReplicas() : 0
        );
        return dto;
    }
}

