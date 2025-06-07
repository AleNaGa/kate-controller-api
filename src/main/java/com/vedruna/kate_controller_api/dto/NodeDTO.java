package com.vedruna.kate_controller_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(description = "Información de un nodo del cluster Kubernetes")
public class NodeDTO {

    @Schema(description = "Nombre del nodo", example = "node-01")
    private String name;

    @Schema(description = "Estado actual del nodo", example = "Ready")
    private String status;

    @Schema(description = "Dirección IP del nodo", example = "192.168.1.10")
    private String ip;

    @Schema(description = "Uso de CPU del nodo", example = "250m")
    private String cpu;

    @Schema(description = "Uso de memoria del nodo", example = "512Mi")
    private String memory;

    @Schema(description = "Lista de pods corriendo en el nodo")
    private List<PodRecordDTO> pods;
}


