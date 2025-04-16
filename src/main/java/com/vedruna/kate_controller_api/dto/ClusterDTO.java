package com.vedruna.kate_controller_api.dto;

import java.util.List;

import lombok.Data;

@Data
class ClusterStateDTO {
    List<NodeDTO> nodes;
    List<DeploymentDTO> deployments;
    List<ServiceDTO> services;
    List<NamespaceDTO> namespaces;
}

