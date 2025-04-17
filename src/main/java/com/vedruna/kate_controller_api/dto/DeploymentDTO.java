package com.vedruna.kate_controller_api.dto;

import lombok.Data;

@Data
public class DeploymentDTO {
    private String name;
    private String namespace;
    private int replicas;
    private int availableReplicas;

}

