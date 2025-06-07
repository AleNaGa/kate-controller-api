package com.vedruna.kate_controller_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Información de un puerto expuesto por un servicio Kubernetes")
public class PortDTO {

    @Schema(description = "Puerto expuesto por el servicio", example = "80")
    private int port;

    @Schema(description = "Puerto al que se dirige el tráfico dentro del pod", example = "8080")
    private int targetPort;
}

