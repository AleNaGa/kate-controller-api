package com.vedruna.kate_controller_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Información de un deployment en Kubernetes")
public class DeploymentDTO {

    @Schema(description = "Nombre del deployment", example = "mi-deployment")
    private String name;

    @Schema(description = "Namespace al que pertenece el deployment", example = "default")
    private String namespace;

    @Schema(description = "Número total de réplicas configuradas", example = "3")
    private int replicas;

    @Schema(description = "Número de réplicas disponibles actualmente", example = "2")
    private int availableReplicas;
}


