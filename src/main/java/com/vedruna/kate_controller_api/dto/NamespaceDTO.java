package com.vedruna.kate_controller_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Información de un namespace en Kubernetes")
public class NamespaceDTO {

    @Schema(description = "Nombre del namespace", example = "default")
    private String name;
}

