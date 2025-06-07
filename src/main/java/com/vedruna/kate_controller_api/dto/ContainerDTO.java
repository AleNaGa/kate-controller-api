package com.vedruna.kate_controller_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Información de un contenedor")
public class ContainerDTO {
   @Schema(description = "Nombre del contenedor", example = "nginx-container")
    private String name;

    @Schema(description = "Imagen del contenedor", example = "nginx:1.21")
    private String image;

    @Schema(description = "Indica si el contenedor está listo (ready)", example = "true")
    private boolean ready;

}

