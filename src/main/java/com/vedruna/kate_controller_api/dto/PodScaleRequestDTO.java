package com.vedruna.kate_controller_api.dto;

import lombok.Data;

@Data
public class PodScaleRequestDTO {
    private String namespace;
    private String deploymentName;
    private int replicas;

}
