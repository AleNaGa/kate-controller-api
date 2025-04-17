package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.DeploymentDTO;

import io.fabric8.kubernetes.api.model.apps.Deployment;

public class DeploymentMapper {
    public static DeploymentDTO toDTO(Deployment deployment) {
        DeploymentDTO dto = new DeploymentDTO();
        dto.setName(deployment.getMetadata().getName());
        dto.setNamespace(deployment.getMetadata().getNamespace());
        dto.setReplicas(deployment.getSpec().getReplicas());
        dto.setAvailableReplicas(
            deployment.getStatus().getAvailableReplicas() != null ?
            deployment.getStatus().getAvailableReplicas() : 0
        );
        return dto;
    }
}

