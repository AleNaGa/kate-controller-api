package com.vedruna.kate_controller_api.dto;


import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "Registro de información de un pod en Kubernetes")
public class PodRecordDTO {

    @Schema(description = "Nombre del pod", example = "mi-pod-12345")
    private String name;

    @Schema(description = "Namespace al que pertenece el pod", example = "default")
    private String namespace;

    @Schema(description = "Estado actual del pod", example = "Running")
    private String status;

    @Schema(description = "Fecha y hora de inicio del pod", example = "2025-06-07T14:30:00Z")
    private String startTime;

    @Schema(description = "Lista de contenedores dentro del pod")
    private List<ContainerDTO> containers;

    @Schema(description = "Nombre del deployment asociado (opcional)", example = "mi-deployment")
    private String deployment;

    @Schema(description = "Lista de servicios asociados (opcional)", example = "[\"servicio1\", \"servicio2\"]")
    private List<String> services;
}
