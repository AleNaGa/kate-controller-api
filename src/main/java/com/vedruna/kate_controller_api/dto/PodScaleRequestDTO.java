package com.vedruna.kate_controller_api.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Datos para escalar un deployment en Kubernetes")
public class PodScaleRequestDTO {

    @Schema(description = "Namespace donde se encuentra el deployment", example = "default")
    private String namespace;

    @Schema(description = "Nombre del deployment a escalar", example = "mi-deployment")
    private String deploymentName;

    @Schema(description = "Número deseado de réplicas para el deployment", example = "3")
    private int replicas;
}

