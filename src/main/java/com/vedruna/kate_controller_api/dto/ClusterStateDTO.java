package com.vedruna.kate_controller_api.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Estado actual del cluster de Kubernetes")
public class ClusterStateDTO {

    @Schema(description = "Lista de nodos del cluster")
    private List<NodeDTO> nodes;

    @Schema(description = "Lista de deployments en el cluster")
    private List<DeploymentDTO> deployments;

    @Schema(description = "Lista de servicios del cluster")
    private List<ServiceDTO> services;

    @Schema(description = "Lista de namespaces del cluster")
    private List<NamespaceDTO> namespaces;
}

